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
      status: "SENT_TO_BANK",
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
      status: "RESOLVED",
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
        q.id === id && q.status !== "RESOLVED"
          ? { ...q, status: "SENT_TO_BANK" }
          : q
      )
    );
  };

  const getStatusStyle = (status) => {
    if (status === "RESOLVED") {
      return { background: "#dcfce7", color: "#166534" };
    }
    if (status === "SENT_TO_BANK") {
      return { background: "#fef3c7", color: "#92400e" };
    }
    return { background: "#e5e7eb", color: "#374151" };
  };

  return (
    <div style={styles.page}>
      <h2 style={styles.heading}>
        Support Dashboard – Chargeback Management
      </h2>

      {/* FULL WIDTH TABLE – no right blank space */}
      <div style={styles.tableWrapper}>
        <table style={styles.table}>
          <thead>
            <tr>
              <th style={styles.th}>Query ID</th>
              <th style={styles.th}>Merchant</th>
              <th style={styles.th}>Merchant VPA</th>
              <th style={styles.th}>UTR</th>
              <th style={styles.th}>Txn Amount</th>
              <th style={styles.th}>Dispute Amount</th>
              <th style={styles.th}>Bank</th>
              <th style={styles.th}>Status</th>
              <th style={styles.th}>Date Raised</th>
              <th style={styles.th}>Action</th>
            </tr>
          </thead>

          <tbody>
            {queries.map((q, index) => (
              <tr
                key={q.id}
                style={{
                  ...styles.tr,
                  background: index % 2 === 0 ? "#ffffff" : "#fafafa"
                }}
              >
                <td style={styles.td}>{q.id}</td>
                <td style={styles.td}>{q.merchantName}</td>
                <td style={styles.td}>{q.merchantVPA}</td>
                <td style={styles.td}>{q.utr}</td>
                <td style={styles.td}>₹{q.txnAmount.toLocaleString()}</td>
                <td style={styles.td}>₹{q.disputeAmount.toLocaleString()}</td>
                <td style={styles.td}>{q.bank}</td>

                <td style={styles.td}>
                  <span style={{ ...styles.status, ...getStatusStyle(q.status) }}>
                    {q.status}
                  </span>
                </td>

                <td style={styles.td}>{q.dateRaised}</td>

                {/* ACTION COLUMN – FIXED WIDTH */}
                <td style={styles.actionTd}>
                  <button
                    style={styles.resolveBtn}
                    onClick={() => resolveQuery(q.id)}
                  >
                    Resolve
                  </button>

                  <button
                    style={{
                      ...styles.bankBtn,
                      ...(q.status === "RESOLVED" ? styles.disabledBtn : {})
                    }}
                    disabled={q.status === "RESOLVED"}
                    onClick={() => sendToBank(q.id)}
                  >
                    Send to Bank
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

const styles = {
  page: {
    padding: "28px",
    background: "#f7f8fa",
    minHeight: "100vh",
    fontFamily: "Inter, system-ui, Arial",
    width: "100%"
  },
  heading: {
    marginBottom: "16px",
    color: "#111827",
    fontWeight: 600
  },
  tableWrapper: {
    background: "#ffffff",
    borderRadius: "10px",
    boxShadow: "0 8px 24px rgba(0,0,0,0.06)",
    width: "100%",
    overflowX: "auto"   // 🔥 fixes right side space
  },
  table: {
    width: "100%",
    borderCollapse: "collapse" // 🔥 no fixed layout
  },
  th: {
    padding: "12px",
    background: "#0f172a",
    color: "#ffffff",
    fontSize: "13px",
    fontWeight: 500,
    textAlign: "left",
    whiteSpace: "nowrap"
  },
  tr: {
    borderBottom: "1px solid #eef0f3"
  },
  td: {
    padding: "12px",
    fontSize: "14px",
    color: "#1f2937",
    verticalAlign: "middle",
    whiteSpace: "nowrap"
  },
  actionTd: {
    padding: "12px",
    display: "flex",
    gap: "8px",
    justifyContent: "center",
    minWidth: "240px"
  },
  status: {
    padding: "4px 10px",
    borderRadius: "999px",
    fontSize: "12px",
    fontWeight: 500
  },
  resolveBtn: {
    width: "110px",
    padding: "6px 0",
    background: "#16a34a",
    color: "#fff",
    border: "none",
    borderRadius: "6px",
    fontSize: "13px",
    cursor: "pointer"
  },
  bankBtn: {
    width: "110px",
    padding: "6px 0",
    background: "#2563eb",
    color: "#fff",
    border: "none",
    borderRadius: "6px",
    fontSize: "13px",
    cursor: "pointer"
  },
  disabledBtn: {
    background: "#9ca3af",
    cursor: "not-allowed"
  }
};

export default Support;
