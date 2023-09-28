import { Box, useTheme, Button } from "@mui/material";
import { useState, useEffect } from "react";
import { tokens } from "../../theme";
import Header from "../../components/Header";
import { DataGrid } from "@mui/x-data-grid";
import axios from "axios";

const IndividualCustomerList = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [customers, setCustomers] = useState([]);

  const getCustomers = () => {
    axios
      .get("http://localhost:8080/api/v1/individual-customers", {
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
      .delete(`http://localhost:8080/api/v1/individual-customers/${row.id}`, {
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
    { field: "name", headerName: "Nome", flex: 1 },
    { field: "mcc", headerName: "MCC" },
    { field: "cpf", headerName: "CPF", flex: 1 },
    { field: "email", headerName: "Email", flex: 1 },
    {
      field: "actions",
      headerName: "Ações",
      flex: 1,
      renderCell: (params) => {
        return (
          <Button
            color="secondary"
            onClick={(e) => deleteCustomer(e, params.row)}
            variant="contained"
          >
            Deletar
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

export default IndividualCustomerList;
