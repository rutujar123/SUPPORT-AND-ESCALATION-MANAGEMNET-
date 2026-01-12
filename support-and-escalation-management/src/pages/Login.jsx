import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Login.css";

const Login = () => {
  const navigate = useNavigate();
  const [form, setForm] = useState({ username: "", password: "" });

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();

    // Hardcoded users
    const users = [
      { username: "merchant1", password: "Merchant@123", role: "MERCHANT" },
      { username: "support1", password: "Support@123", role: "SUPPORT" },
    ];

    const user = users.find(u => u.username === form.username && u.password === form.password);

    if (!user) {
      alert("Invalid credentials!");
      return;
    }

    // Store user temporarily in localStorage
    localStorage.setItem("user", JSON.stringify(user));

    // Redirect based on role
    if (user.role === "MERCHANT") navigate("/merchant-dashboard");
    else if (user.role === "SUPPORT") navigate("/support");
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <input name="username" placeholder="Username" onChange={handleChange} required />
        <input name="password" type="password" placeholder="Password" onChange={handleChange} required />
        <button type="submit">Login</button>
      </form>
      <p>
        Don't have an account? <span className="link">Register here</span>
      </p>
    </div>
  );
};

export default Login;
