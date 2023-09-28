import { useState } from "react";
import { ColorModeContext, useMode } from "./theme";
import { CssBaseline, ThemeProvider } from "@mui/material";
import { Routes, Route } from "react-router-dom";
import Topbar from "./scenes/global/Topbar";
import Sidebar from "./scenes/global/Sidebar";
import LegalEntityCustomerList from "./scenes/legalentitycustomerlist";
import AddLegalEntityCustomerForm from "./scenes/addlegalentitycustomerform";
import AddIndividualCustomerForm from "./scenes/addindividualcustomerform";
import EditLegalEntityCustomerForm from "./scenes/editlegalentitycustomerform";
import EditIndividualCustomerForm from "./scenes/editindividualcustomerform";
import IndividualCustomerList from "./scenes/individualcustomerlist";
import ProspectLegalEntityCustomer from "./scenes/prospectlegalentitycustomer";
import ProspectIndividualCustomer from "./scenes/prospectindividualcustomer";

function App() {
  const [theme, colorMode] = useMode();
  const [isSidebar, setIsSidebar] = useState(true);

  return (
    <ColorModeContext.Provider value={colorMode}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <div className="app">
          <Sidebar isSidebar={isSidebar} />
          <main className="content">
            <Topbar />

            <Routes>
              <Route
                path="/pessoa-juridica/clientes"
                element={<LegalEntityCustomerList />}
              />
              <Route
                path="/pessoa-juridica/cadastro"
                element={<AddLegalEntityCustomerForm />}
              />
              <Route
                path="/pessoa-juridica/clientes/:id"
                element={<EditLegalEntityCustomerForm />}
              />
              <Route
                path="/pessoa-juridica/prospectar"
                element={<ProspectLegalEntityCustomer />}
              />
              <Route
                path="/pessoa-fisica/clientes"
                element={<IndividualCustomerList />}
              />
              <Route
                path="/pessoa-fisica/cadastro"
                element={<AddIndividualCustomerForm />}
              />
              <Route
                path="/pessoa-fisica/clientes/:id"
                element={<EditIndividualCustomerForm />}
              />
              <Route
                path="/pessoa-fisica/prospectar"
                element={<ProspectIndividualCustomer />}
              />
            </Routes>
          </main>
        </div>
      </ThemeProvider>
    </ColorModeContext.Provider>
  );
}

export default App;
