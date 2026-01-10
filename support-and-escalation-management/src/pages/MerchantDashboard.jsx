import React, { useState } from "react";
import ChargebackForm from "../components/ChargebackForm";
import QueryList from "../components/QueryList";
import "./MerchantDashboard.css";

const MerchantDashboard = () => {
  const [queries, setQueries] = useState([]);

  return (
    <div className="dashboard">
      <h1>Merchant Dashboard</h1>

      <ChargebackForm
        onAddQuery={(q) => setQueries(prev => [...prev, q])}
      />

      <QueryList queries={queries} />
    </div>
  );
};

export default MerchantDashboard;
