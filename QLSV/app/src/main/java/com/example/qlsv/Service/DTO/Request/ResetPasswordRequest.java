package com.example.qlsv.Service.DTO.Request;

public class ResetPasswordRequest {
    private String email;
    private String otp;
    private String newPassword;

    public ResetPasswordRequest(String email,String otp, String newPassword)
    {
        this.email = email;
        this.otp = otp;
        this.newPassword = newPassword;
    }
    // Getter & Setter
    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }

    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}