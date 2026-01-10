import React from "react";
import "./QueryList.css";

const QueryList = ({ queries }) => {
  return (
    <div className="card">
      <h2>Your Chargeback Queries</h2>

      {queries.length === 0 ? (
        <p>No chargebacks raised yet.</p>
      ) : (
        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Transaction Ref</th>
              <th>Disputed Amount</th>
              <th>Status</th>
              <th>Date Raised</th>
            </tr>
          </thead>
          <tbody>
            {queries.map((q) => (
              <tr key={q.id}>
                <td>{q.id}</td>
                <td>{q.transactionRef}</td>
                <td>₹{q.disputedAmount}</td>
                <td>
                  <span className={`status ${q.status}`}>{q.status}</span>
                </td>
                <td>{q.date}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default QueryList;
