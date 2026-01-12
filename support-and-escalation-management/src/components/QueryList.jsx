import React from "react";
import "./QueryList.css";

const QueryList = ({ queries }) => {
  const formatStatus = (status) => {
    return status
      .toLowerCase()
      .split("_")
      .map(word => word.charAt(0).toUpperCase() + word.slice(1))
      .join(" ");
  };

  return (
    <div className="card">
      <h2>Your Chargeback Queries</h2>

      {queries.length === 0 ? (
        <p className="empty-state">
          No chargebacks raised yet.
        </p>
      ) : (
        <div className="table-wrapper">
          <table className="table">
            <thead>
              <tr>
                <th>Sr No</th>
                <th>Transaction Ref</th>
                <th>Disputed Amount</th>
                <th>Status</th>
                <th>Date Raised</th>
              </tr>
            </thead>

            <tbody>
              {queries.map((q, index) => (
                <tr key={q.id}>
                  <td className="sr">{index + 1}</td>

                  <td className="mono">
                    {q.transactionRef}
                  </td>

                  <td className="amount">
                    ₹ {q.disputedAmount}
                  </td>

                  <td>
                    <span className={`status ${q.status}`}>
                      {formatStatus(q.status)}
                    </span>
                  </td>

                  <td className="date">
                    {q.date}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default QueryList;
