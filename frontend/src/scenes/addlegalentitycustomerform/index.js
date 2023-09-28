import { Box, Button, TextField } from "@mui/material";
import MuiAlert from "@mui/material/Alert";
import Snackbar from "@mui/material/Snackbar";
import { useState, forwardRef } from "react";
import { Formik } from "formik";
import * as yup from "yup";
import useMediaQuery from "@mui/material/useMediaQuery";
import Header from "../../components/Header";
import axios from "axios";

const Alert = forwardRef(function Alert(props, ref) {
  return (
    <MuiAlert
      elevation={6}
      ref={ref}
      variant="filled"
      {...props}
    />
  );
});

const Form = () => {
  const isNonMobile = useMediaQuery("(min-width:600px)");
  const [open, setOpen] = useState(false);

  const initialValues = {
    corporateName: "",
    cnpj: "",
    mcc: "",
    contactName: "",
    contactCpf: "",
    email: "",
  };

  const validations = yup.object().shape({
    corporateName: yup
      .string()
      .required("Campo obrigatório")
      .max(50, "Máximo de 50 caracteres"),
    cnpj: yup
      .string()
      .required("Campo obrigatório")
      .min(14, "Precisa ter 14 digitos")
      .max(14, "Precisa ter 14 digitos"),
    mcc: yup
      .string()
      .required("Campo obrigatório")
      .min(4, "Precisa ter 4 digitos")
      .max(4, "Precisa ter 4 digitos"),
    contactName: yup
      .string()
      .required("Campo obrigatório")
      .max(50, "Máximo de 50 caracteres"),
    contactCpf: yup
      .string()
      .required("Campo obrigatório")
      .min(11, "Precisa ter 11 digitos")
      .max(11, "Precisa ter 11 digitos"),
    email: yup
      .string()
      .email("Email não é válido")
      .required("Campo obrigatório"),
  });

  const addCustomer = (values) => {
    console.log(values);
    axios
      .post("http://localhost:8080/api/v1/legal-entity-customers", values, {
        auth: {
          username: "user",
          password: "user",
        },
      })
      .then((response) => {
        if (response.status === 201) {
          setOpen(true);
        }
        // TO DO - if status not 201, show error alert
        // TO DO - reset values
      });
  };

  const handleClose = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }

    setOpen(false);
  };

  return (
    <Box m="20px">
      <Header
        title="Pessoa Jurídica"
        subtitle="Cadastrar novo cliente"
      />

      <Formik
        onSubmit={addCustomer}
        initialValues={initialValues}
        validationSchema={validations}
      >
        {({
          values,
          errors,
          touched,
          handleBlur,
          handleChange,
          handleSubmit,
        }) => (
          <form onSubmit={handleSubmit}>
            <Box
              display="grid"
              gap="30px"
              gridTemplateColumns="repeat(4, minmax(0, 1fr))"
              sx={{
                "& > div": { gridColumn: isNonMobile ? undefined : "span 4" },
              }}
            >
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="Razão Social"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.corporateName}
                name="corporateName"
                error={!!touched.corporateName && !!errors.corporateName}
                helperText={touched.corporateName && errors.corporateName}
                sx={{ gridColumn: "span 2" }}
              />
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="CNPJ (somente número, sem pontuações)"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.cnpj}
                name="cnpj"
                error={!!touched.cnpj && !!errors.cnpj}
                helperText={touched.cnpj && errors.cnpj}
                sx={{ gridColumn: "span 2" }}
              />
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="MCC"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.mcc}
                name="mcc"
                error={!!touched.mcc && !!errors.mcc}
                helperText={touched.mcc && errors.mcc}
                sx={{ gridColumn: "span 4" }}
              />
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="Nome do contato do estabelecimento"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.contactName}
                name="contactName"
                error={!!touched.contactName && !!errors.contactName}
                helperText={touched.contactName && errors.contactName}
                sx={{ gridColumn: "span 4" }}
              />
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="CPF do contato do estabelecimento (somente número, sem pontuações)"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.contactCpf}
                name="contactCpf"
                error={!!touched.contactCpf && !!errors.contactCpf}
                helperText={touched.contactCpf && errors.contactCpf}
                sx={{ gridColumn: "span 4" }}
              />
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="Email"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.email}
                name="email"
                error={!!touched.email && !!errors.email}
                helperText={touched.email && errors.email}
                sx={{ gridColumn: "span 4" }}
              />
            </Box>
            <Box
              display="flex"
              justifyContent="end"
              mt="20px"
            >
              <Button
                type="submit"
                color="secondary"
                variant="contained"
              >
                Cadastrar cliente
              </Button>
            </Box>
          </form>
        )}
      </Formik>
      <Snackbar
        open={open}
        autoHideDuration={6000}
        onClose={handleClose}
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
      >
        <Alert
          onClose={handleClose}
          severity="success"
          sx={{ width: "100%" }}
        >
          Cliente salvo com sucesso!
        </Alert>
      </Snackbar>
    </Box>
  );
};

export default Form;
