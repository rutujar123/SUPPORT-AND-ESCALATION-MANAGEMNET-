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
    setForm((prev) => ({
      ...prev,
      [name]: files ? files[0] : value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const newQuery = {
      id: Date.now(),
      transactionRef: form.transactionRef,
      disputedAmount: form.disputedAmount,
      status: "NEW",
      date: form.transactionDate,
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

        <div className="field">
          <label>Transaction Ref / UTR</label>
          <input
            name="transactionRef"
            value={form.transactionRef}
            onChange={handleChange}
            required
          />
        </div>

        <div className="field">
          <label>Order ID / Movino Txn ID</label>
          <input
            name="orderId"
            value={form.orderId}
            onChange={handleChange}
            required
          />
        </div>

        <div className="field">
          <label>Transaction Date</label>
          <input
            type="date"
            name="transactionDate"
            value={form.transactionDate}
            onChange={handleChange}
            required
          />
        </div>

        <div className="field">
          <label>Transaction Amount</label>
          <input
            type="number"
            name="transactionAmount"
            value={form.transactionAmount}
            onChange={handleChange}
            required
          />
        </div>

        <div className="field">
          <label>Payment Type / Network</label>
          <input
            name="paymentType"
            value={form.paymentType}
            onChange={handleChange}
          />
        </div>

        <div className="field">
          <label>Customer Name</label>
          <input
            name="customerName"
            value={form.customerName}
            onChange={handleChange}
          />
        </div>

        <div className="field">
          <label>Customer Email / VPA</label>
          <input
            name="customerEmail"
            value={form.customerEmail}
            onChange={handleChange}
          />
        </div>

        <div className="field">
          <label>Disputed Amount</label>
          <input
            type="number"
            name="disputedAmount"
            value={form.disputedAmount}
            onChange={handleChange}
            required
          />
        </div>

        <div className="field full">
          <label>Reason / Sub-category</label>
          <input
            name="reason"
            value={form.reason}
            onChange={handleChange}
            required
          />
        </div>

        <div className="field full">
          <label>Upload Supporting Document</label>
          <input
            type="file"
            name="attachment"
            onChange={handleChange}
          />
        </div>

        <button type="submit" className="submit-btn">
          Submit Chargeback
        </button>
      </form>
    </div>
  );
};

export default ChargebackForm;
