import { Box, useTheme, Button } from "@mui/material";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import { useState, useEffect } from "react";
import { tokens } from "../../theme";
import Header from "../../components/Header";
import { DataGrid } from "@mui/x-data-grid";
import Stack from "@mui/material/Stack";
import { useNavigate } from "react-router-dom";

import axios from "axios";

const IndividualCustomerList = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const navigate = useNavigate();
  const [customers, setCustomers] = useState([]);
  const [dialog, setDialog] = useState({ message: "" });
  const [open, setOpen] = useState(false);

  const handleClose = () => {
    setOpen(false);
  };

  const getCustomers = () => {
    axios
      .get("http://localhost:8080/api/v1/individual-customers", {
        auth: {
          username: "user",
          password: "user",
        },
      })
      .then((response) => {
        if (response.status === 200) {
          setCustomers(response.data);
        } else {
          setDialog({ message: "Ocorreu um erro inesperado" });
          setOpen(true);
        }
      })
      .catch((err) => {
        setDialog({
          message: `Ocorreu um erro inesperado: ${err}`,
        });
        setOpen(true);
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
        if (response.status === 200) {
          getCustomers();
          setDialog({ message: "Cliente deletado com sucesso" });
          setOpen(true);
        } else {
          setDialog({ message: "Ocorreu um erro inesperado" });
          setOpen(true);
        }
      })
      .catch((err) => {
        setDialog({
          message: `Ocorreu um erro inesperado: ${err}`,
        });
        setOpen(true);
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
          <Stack
            spacing={1}
            direction="row"
          >
            <Button
              color="secondary"
              onClick={(e) =>
                navigate(`/pessoa-fisica/clientes/${params.row.id}`)
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
        title="Pessoa Física"
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
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            {dialog.message}
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Ok</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default IndividualCustomerList;
