package com.example.API.Service;

import com.example.API.Entity.AdminUser;
import com.example.API.Entity.PasswordResetToken;
import com.example.API.Repository.AdminUserRepository;
import com.example.API.Repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class PasswordResetTokenService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    public String generateAndSendOTP(String email) {
        Optional<AdminUser> userOpt = adminUserRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return "Email không tồn tại";
        }

        // Tạo mã OTP 6 chữ số
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(10);

        PasswordResetToken token = new PasswordResetToken();
        token.setEmail(email);
        token.setToken(otp); // đây chính là mã OTP
        token.setExpiry(expiry);
        tokenRepository.save(token);

        // Gửi OTP đến email
        mailService.sendOtpEmail(email, otp);

        return "OTP đã được gửi đến email.";
    }

    public String resetPassword(String email, String otp, String newPassword) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(otp);
        if (tokenOpt.isEmpty()) {
            return "Mã OTP không hợp lệ";
        }

        PasswordResetToken resetToken = tokenOpt.get();

        // Kiểm tra email khớp với mã OTP
        if (!resetToken.getEmail().equals(email)) {
            return "Email không khớp với mã OTP";
        }

        // Kiểm tra thời hạn
        if (resetToken.getExpiry().isBefore(LocalDateTime.now())) {
            return "Mã OTP đã hết hạn";
        }

        // Cập nhật mật khẩu
        Optional<AdminUser> userOpt = adminUserRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return "Tài khoản không tồn tại";
        }

        AdminUser user = userOpt.get();
        user.setPassword(newPassword); // bạn có thể thêm mã hóa nếu cần
        adminUserRepository.save(user);
        tokenRepository.delete(resetToken); // Xoá mã sau khi dùng

        return "Đổi mật khẩu thành công";
    }
}
