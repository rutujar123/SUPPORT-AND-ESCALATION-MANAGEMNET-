import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Register.css";

const Register = () => {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    username: "",
    password: "",
    role: "",
    bankName: "",
    accountNumber: "",
    ifsc: ""
  });

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!form.username || !form.password || !form.role) {
      alert("Please fill all required fields!");
      return;
    }

    if (form.role === "MERCHANT") {
      if (!form.bankName || !form.accountNumber || !form.ifsc) {
        alert("Please fill all bank details for merchant!");
        return;
      }
    }

    const users = JSON.parse(localStorage.getItem("users")) || [];
    users.push(form);
    localStorage.setItem("users", JSON.stringify(users));

    alert("Registration successful!");
    navigate("/login");
  };

  return (
    <div className="register-container">
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <label>Role:</label>
        <select name="role" value={form.role} onChange={handleChange} required>
          <option value="">Select Role</option>
          <option value="MERCHANT">Merchant</option>
          <option value="SUPPORT">Support</option>
        </select>

        <input name="username" placeholder="Username" onChange={handleChange} required />
        <input name="password" type="password" placeholder="Password" onChange={handleChange} required />

        {form.role === "MERCHANT" && (
          <>
            <input name="bankName" placeholder="Bank Name" onChange={handleChange} required />
            <input name="accountNumber" placeholder="Account Number" onChange={handleChange} required />
            <input name="ifsc" placeholder="IFSC Code" onChange={handleChange} required />
          </>
        )}

        <button type="submit">Register</button>
      </form>

      <p>
        Already have an account? <span className="link" onClick={() => navigate("/login")}>Login here</span>
      </p>
    </div>
  );
};

export default Register;
