import { ColorModeContext, useMode } from "./theme";
import { CssBaseline, ThemeProvider } from "@mui/material";
import { Routes, Route } from "react-router-dom";
import Topbar from "./scenes/global/Topbar";
import Sidebar from "./scenes/global/Sidebar";
import Home from "./scenes/home";
// import AddLegalEntityCustomerForm from "./scenes/addlegalentitycustomerform";
// import AddIndividualCustomerForm from "./scenes/addindividualcustomerform";
// import EditLegalEntityCustomerForm from "./scenes/editlegalentitycustomerform";
// import EditIndividualCustomerForm from "./scenes/editindividualcustomerform";
// import LegalEntityCustomerList from "./scenes/legalentitycustomerlist";
// import IndividualCustomerList from "./scenes/individualcustomerlist";
// import ProspectLegalEntityCustomer from "./scenes/prospectlegalentitycustomer";
// import ProspectIndividualCustomer from "./scenes/prospectindividualcustomer";

function App() {
  const [theme, colorMode] = useMode();

  return (
    <ColorModeContext.Provider value={colorMode}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <main className="content">
          <Topbar />

          <Routes>
            <Route
              path="/"
              element={<Home />}
            />
            {/* <Route
              path="/legal-entity-customer/add-customer-form"
              element={<AddLegalEntityCustomerForm />}
            /> */}
            {/* <Route
              path="/legal-entity-customer/update-customer-form"
              element={<EditLegalEntityCustomerForm />}
            /> */}
            {/* <Route
              path="/legal-entity-customer/list"
              element={<LegalEntityCustomerList />}
            /> */}
            {/* <Route
              path="/legal-entity-customer/prospect"
              element={<ProspectLegalEntityCustomer />}
            /> */}
            {/* <Route
              path="/individual-customer/add-customer-form"
              element={<AddIndividualCustomerForm />}
            /> */}
            {/* <Route
              path="/individual-customer/update-customer-form"
              element={<EditIndividualCustomerForm />}
            /> */}
            {/* <Route
              path="/individual-customer/list"
              element={<IndividualCustomerList />}
            /> */}
            {/* <Route
              path="/individual-customer/prospect"
              element={<ProspectIndividualCustomer />}
            /> */}
          </Routes>
        </main>
      </ThemeProvider>
    </ColorModeContext.Provider>
  );
}

export default App;
