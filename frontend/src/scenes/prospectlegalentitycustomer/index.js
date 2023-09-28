import { Box, useTheme, Button, Grid } from "@mui/material";
import { useState, useEffect } from "react";
import { tokens } from "../../theme";
import Header from "../../components/Header";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";

const ProspectLegalEntityCustomer = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const navigate = useNavigate();
  const [customer, setCustomer] = useState([]);

  const prospectCustomer = () => {
    axios
      .get("http://localhost:8080/api/v1/legal-entity-customers/next", {
        auth: {
          username: "user",
          password: "user",
        },
      })
      .then((response) => {
        console.log(response);
        setCustomer(response.data);
      });
  };

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
        sx={{ minHeight: "100vh" }}
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
          <Card sx={{ minWidth: 275, backgroundColor: colors.primary[500] }}>
            <CardContent>
              <Typography
                sx={{ fontSize: 14 }}
                color="text.secondary"
                gutterBottom
              >
                Pessoa Jurídica
              </Typography>
              <Typography
                vsx={{ mb: 2.0 }}
                component="div"
              >
                {customer.corporateName}
              </Typography>
              <Typography
                sx={{ mb: 2.0 }}
                component="div"
                color="text.secondary"
              >
                CNPJ: {customer.cnpj}
              </Typography>
              <Typography
                sx={{ mb: 2.0 }}
                color="text.secondary"
              >
                MCC: {customer.mcc}
              </Typography>
              <Typography
                sx={{ mb: 2.0 }}
                color="text.secondary"
              >
                Nome para contato: {customer.contactName}
              </Typography>
              <Typography
                sx={{ mb: 2.0 }}
                color="text.secondary"
              >
                CPF do contato: {customer.contactCpf}
              </Typography>
              <Typography
                sx={{ mb: 2.0 }}
                color="text.secondary"
              >
                {customer.email}
              </Typography>
            </CardContent>
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
};

export default ProspectLegalEntityCustomer;
