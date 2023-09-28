import { Box, useTheme, Button } from "@mui/material";
import { useState, useEffect } from "react";
import { tokens } from "../../theme";
import Header from "../../components/Header";
import { DataGrid } from "@mui/x-data-grid";
import axios from "axios";
import Stack from "@mui/material/Stack";
import { useNavigate } from "react-router-dom";

const LegalEntityCustomerList = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const navigate = useNavigate();
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
    { field: "corporateName", headerName: "Razão Social", flex: 1 },
    { field: "cnpj", headerName: "CNPJ", flex: 1 },
    { field: "mcc", headerName: "MCC" },
    { field: "contactName", headerName: "Nome Contato", flex: 1 },
    { field: "contactCpf", headerName: "CPF Contato", flex: 1 },
    { field: "email", headerName: "Email", flex: 1 },
    {
      field: "actions",
      headerName: "Ações",
      flex: 1,
      renderCell: (params) => {
        return (
          <Stack
            spacing={1}
            direction="row"
          >
            <Button
              color="secondary"
              onClick={(e) =>
                navigate(`/pessoa-juridica/clientes/${params.row.id}`)
              }
              variant="outlined"
            >
              Editar
            </Button>
            <Button
              color="error"
              onClick={(e) => deleteCustomer(e, params.row)}
              variant="outlined"
            >
              Deletar
            </Button>
          </Stack>
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
