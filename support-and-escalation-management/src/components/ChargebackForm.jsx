import React, { useState } from "react";
import "./ChargebackForm.css";

const ChargebackForm = ({ onAddQuery }) => {
  const [form, setForm] = useState({
    transactionRef: "",
    orderId: "",
    transactionDate: "",
    transactionAmount: "",
    paymentType: "",
    customerName: "",
    customerEmail: "",
    disputedAmount: "",
    reason: "",
    attachment: null,
  });

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    setForm({ ...form, [name]: files ? files[0] : value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const newQuery = {
      id: Date.now(),
      transactionRef: form.transactionRef,
      disputedAmount: form.disputedAmount,
      status: "NEW",
      date: new Date().toLocaleString(),
    };

    onAddQuery(newQuery);

    setForm({
      transactionRef: "",
      orderId: "",
      transactionDate: "",
      transactionAmount: "",
      paymentType: "",
      customerName: "",
      customerEmail: "",
      disputedAmount: "",
      reason: "",
      attachment: null,
    });
  };

  return (
    <div className="card">
      <h2>Raise Chargeback</h2>

      <form onSubmit={handleSubmit} className="form-grid">
        <input name="transactionRef" placeholder="Transaction Ref / UTR" onChange={handleChange} required />
        <input name="orderId" placeholder="Order ID / Movino Txn ID" onChange={handleChange} required />

        <input type="datetime-local" name="transactionDate" onChange={handleChange} required />
        <input type="number" name="transactionAmount" placeholder="Transaction Amount" onChange={handleChange} required />

        <input name="paymentType" placeholder="Payment Type / Network" onChange={handleChange} />
        <input name="customerName" placeholder="Customer Name" onChange={handleChange} />

        <input name="customerEmail" placeholder="Customer Email / VPA" onChange={handleChange} />
        <input type="number" name="disputedAmount" placeholder="Disputed Amount" onChange={handleChange} required />

        <input name="reason" placeholder="Reason / Sub-category" onChange={handleChange} required />

        <input type="file" name="attachment" />

        <button type="submit" className="btn">Submit Chargeback</button>
      </form>
    </div>
  );
};

export default ChargebackForm;
