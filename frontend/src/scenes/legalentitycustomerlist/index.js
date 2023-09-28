import { Box, useTheme, Button } from "@mui/material";
import { useState, useEffect } from "react";
import { tokens } from "../../theme";
import Header from "../../components/Header";
import { DataGrid } from "@mui/x-data-grid";
import axios from "axios";

const LegalEntityCustomerList = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [customers, setCustomers] = useState([]);

  const getCustomers = () => {
    axios
      .get("http://localhost:8080/api/v1/legal-entity-customers", {
        auth: {
          username: "user",
          password: "user",
        },
      })
      .then((response) => {
        setCustomers(response.data);
      });
  };

  useEffect(() => {
    getCustomers();
  }, []);

  const deleteCustomer = (e, row) => {
    e.stopPropagation();

    axios
      .delete(`http://localhost:8080/api/v1/legal-entity-customers/${row.id}`, {
        auth: {
          username: "user",
          password: "user",
        },
      })
      .then((response) => {
        getCustomers();
      });
  };

  const columns = [
    { field: "id", headerName: "ID" },
    { field: "corporateName", headerName: "Razão Social", minWidth: 200 },
    { field: "cnpj", headerName: "CNPJ", minWidth: 200 },
    { field: "mcc", headerName: "MCC" },
    { field: "contactName", headerName: "Nome Contato", minWidth: 200 },
    { field: "contactCpf", headerName: "CPF Contato", minWidth: 200 },
    { field: "email", headerName: "Email", minWidth: 200 },
    {
      field: "actions",
      headerName: "Actions",
      width: 400,
      renderCell: (params) => {
        return (
          <Button
            color="secondary"
            onClick={(e) => deleteCustomer(e, params.row)}
            variant="contained"
          >
            Delete
          </Button>
        );
      },
    },
  ];

  return (
    <Box m="20px">
      <Header
        title="Pessoa Jurídica"
        subtitle="Lista de clientes"
      />
      <Box
        m="40px 0 0 0"
        height="75vh"
      >
        <DataGrid
          autoHeight
          hideFooterPagination
          rowSpacingType="border"
          rows={customers}
          columns={columns}
        />
      </Box>
    </Box>
  );
};

export default LegalEntityCustomerList;
