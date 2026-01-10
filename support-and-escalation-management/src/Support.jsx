import { useState } from "react";

function Support() {
  const [queries, setQueries] = useState([
    {
      id: 1,
      merchantName: "ABC Store",
      merchantVPA: "abc@upi",
      utr: "UTR123456",
      txnAmount: 1500,
      disputeAmount: 1500,
      bank: "SBI",
      status: "NEW",
      reason: "Payment Failed",
      dateRaised: "10-01-2026"
    },
    {
      id: 2,
      merchantName: "XYZ Mart",
      merchantVPA: "xyz@upi",
      utr: "UTR987654",
      txnAmount: 3200,
      disputeAmount: 3200,
      bank: "HDFC",
      status: "SENT_TO_BANK",
      reason: "Refund Pending",
      dateRaised: "09-01-2026"
    }
  ]);

  const resolveQuery = (id) => {
    setQueries(
      queries.map(q =>
        q.id === id ? { ...q, status: "RESOLVED" } : q
      )
    );
  };

  const sendToBank = (id) => {
    setQueries(
      queries.map(q =>
        q.id === id ? { ...q, status: "SENT_TO_BANK" } : q
      )
    );
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Support Dashboard – Chargeback Management</h2>

      <table border="1" cellPadding="8" width="100%">
        <thead>
          <tr>
            <th>Query ID</th>
            <th>Merchant</th>
            <th>Merchant VPA</th>
            <th>UTR</th>
            <th>Txn Amount</th>
            <th>Dispute Amount</th>
            <th>Bank</th>
            <th>Status</th>
            <th>Date Raised</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {queries.map((q) => (
            <tr key={q.id}>
              <td>{q.id}</td>
              <td>{q.merchantName}</td>
              <td>{q.merchantVPA}</td>
              <td>{q.utr}</td>
              <td>{q.txnAmount}</td>
              <td>{q.disputeAmount}</td>
              <td>{q.bank}</td>
              <td>{q.status}</td>
              <td>{q.dateRaised}</td>
              <td>
                <button onClick={() => resolveQuery(q.id)}>
                  Resolve
                </button>{" "}
                <button onClick={() => sendToBank(q.id)}>
                  Send to Bank
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Support;
