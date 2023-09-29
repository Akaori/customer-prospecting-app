import { Box, Typography, Button, Grid } from "@mui/material";
import { useNavigate } from "react-router-dom";

const PageNotFound = () => {
  const navigate = useNavigate();

  const goToMainPage = () => {
    navigate("/");
  };

  return (
    <Grid
      container
      spacing={0}
      direction="column"
      alignItems="center"
      justifyContent="center"
    >
      <Grid
        item
        xs={3}
        style={{ textAlign: "center" }}
      >
        <Box m="20px">
          <Typography sx={{ mb: 2.0, fontSize: 60 }}>404</Typography>
          <Typography sx={{ mb: 2.0, fontSize: 50 }}>
            Página não encontrada.
          </Typography>
          <Button
            color="secondary"
            variant="contained"
            onClick={goToMainPage}
          >
            Voltar para página inicial
          </Button>
        </Box>
      </Grid>
    </Grid>
  );
};

export default PageNotFound;
