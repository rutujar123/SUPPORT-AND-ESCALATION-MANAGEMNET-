import { BrowserRouter, Routes, Route } from "react-router-dom";
import Support from "./Support";
import MerchantDashboard from "./pages/MerchantDashboard";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/support" element={<Support />} />
        <Route path="/merchant" element={<MerchantDashboard />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
