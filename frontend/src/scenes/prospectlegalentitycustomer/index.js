import { Box, useTheme, Button, Grid } from "@mui/material";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import { useState } from "react";
import { tokens } from "../../theme";
import Header from "../../components/Header";
import axios from "axios";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";

const ProspectLegalEntityCustomer = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [customer, setCustomer] = useState([]);
  const [dialog, setDialog] = useState({ message: "" });
  const [open, setOpen] = useState(false);

  const handleClose = () => {
    setOpen(false);
  };

  const prospectCustomer = () => {
    axios
      .get("http://localhost:8080/api/v1/legal-entity-customers/next", {
        auth: {
          username: "user",
          password: "user",
        },
      })
      .then((response) => {
        if (response.status === 200) {
          setCustomer(response.data);
        } else if (response.status === 204) {
          setCustomer({});
          setDialog({
            message: "Não há clientes na fila no momento. Tente mais tarde.",
          });
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

  function isEmpty(obj) {
    for (const prop in obj) {
      if (Object.hasOwn(obj, prop)) {
        return false;
      }
    }

    return true;
  }

  return (
    <Box m="20px">
      <Header
        title="Pessoa Jurídica"
        subtitle="Prospectar clientes"
      />
      <Grid
        container
        spacing={0}
        direction="column"
      >
        <Grid
          item
          xs={3}
        >
          <Button
            color="secondary"
            onClick={(e) => prospectCustomer()}
            variant="outlined"
          >
            Prospectar cliente
          </Button>
          <Card
            sx={{
              minWidth: 275,
              backgroundColor: colors.primary[400],
              mt: 5.0,
            }}
          >
            <CardContent>
              {!isEmpty(customer) && (
                <div>
                  <Typography
                    sx={{ fontSize: 14 }}
                    color="text.secondary"
                    gutterBottom
                  >
                    Pessoa Jurídica
                  </Typography>
                  <Typography
                    sx={{ fontSize: 24, mb: 2.0 }}
                    component="div"
                  >
                    {customer.corporateName}
                  </Typography>
                  <Typography
                    sx={{ mb: 2.0, fontSize: 20 }}
                    component="div"
                    color="text.secondary"
                  >
                    CNPJ: {customer.cnpj}
                  </Typography>
                  <Typography
                    sx={{ mb: 2.0, fontSize: 20 }}
                    color="text.secondary"
                  >
                    MCC: {customer.mcc}
                  </Typography>
                  <Typography
                    sx={{ mb: 2.0, fontSize: 20 }}
                    color="text.secondary"
                  >
                    Nome para contato: {customer.contactName}
                  </Typography>
                  <Typography
                    sx={{ mb: 2.0, fontSize: 20 }}
                    color="text.secondary"
                  >
                    CPF do contato: {customer.contactCpf}
                  </Typography>
                  <Typography
                    sx={{ mb: 2.0, fontSize: 20 }}
                    color="text.secondary"
                  >
                    {customer.email}
                  </Typography>
                </div>
              )}
              {isEmpty(customer) && (
                <Typography
                  sx={{ fontSize: 22, textAlign: "center" }}
                  color="text.secondary"
                >
                  Clique no botão para prospectar um novo cliente.
                </Typography>
              )}
            </CardContent>
          </Card>
        </Grid>
      </Grid>
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

export default ProspectLegalEntityCustomer;
