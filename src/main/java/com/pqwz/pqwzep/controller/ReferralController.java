package com.pqwz.pqwzep.controller;

import com.pqwz.pqwzep.model.Referral;
import com.pqwz.pqwzep.service.ReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/referrals")
@CrossOrigin(origins = "*")
public class ReferralController {

    @Autowired
    private ReferralService referralService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitReferral(
            @RequestParam("employeeId") Long employeeId,
            @RequestParam("jobOpeningId") Long jobOpeningId,
            @RequestParam("candidateName") String candidateName,
            @RequestPart("resumeFile") MultipartFile resumeFile,
            @RequestParam(value = "comments", required = false) String comments
    ) throws IOException {

        if (resumeFile == null || resumeFile.isEmpty() || !StringUtils.hasText(candidateName)) {
            return ResponseEntity
                    .badRequest()
                    .body("Candidate name and resume file are required.");
        }

        boolean isDuplicate = referralService.isDuplicateReferral(employeeId, jobOpeningId, candidateName);
        if (isDuplicate) {
            return ResponseEntity
                    .status(409)
                    .body("You have already referred this candidate for this job.");
        }

        // ✅ Save file to absolute path to avoid "path not found" error
        String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "resumes";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String original = resumeFile.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "_" + (original != null ? original : "resume.bin");
        File dest = new File(dir, fileName);
        resumeFile.transferTo(dest);

        Referral referral = referralService.createReferral(
                employeeId,
                jobOpeningId,
                candidateName,
                dest.getAbsolutePath(),
                comments
        );

        return ResponseEntity.ok(referral);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Referral>> getByEmployee(@PathVariable Long employeeId) {
        List<Referral> list = referralService.getReferralsByEmployee(employeeId);
        return ResponseEntity.ok(list);
    }
}
