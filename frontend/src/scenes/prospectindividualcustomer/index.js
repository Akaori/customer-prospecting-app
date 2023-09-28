import { Box, useTheme, Button, Grid } from "@mui/material";
import { useState, useEffect } from "react";
import { tokens } from "../../theme";
import Header from "../../components/Header";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";

const ProspectIndividualCustomer = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const navigate = useNavigate();
  const [customer, setCustomer] = useState([]);

  const prospectCustomer = () => {
    axios
      .get("http://localhost:8080/api/v1/individual-customers/next", {
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
        title="Pessoa Física"
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
                    Pessoa Física
                  </Typography>
                  <Typography
                    sx={{ fontSize: 24, mb: 2.0 }}
                    component="div"
                  >
                    {customer.name}
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
                    CPF: {customer.cpf}
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
    </Box>
  );
};

export default ProspectIndividualCustomer;
