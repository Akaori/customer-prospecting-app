import { Box, Button, TextField } from "@mui/material";
import { Formik } from "formik";
import { useEffect, useRef } from "react";
import { useParams } from "react-router-dom";
import * as yup from "yup";
import useMediaQuery from "@mui/material/useMediaQuery";
import Header from "../../components/Header";
import axios from "axios";

const EditIndividualCustomerForm = () => {
  const isNonMobile = useMediaQuery("(min-width:600px)");
  const { id } = useParams();
  const formikRef = useRef();

  const initialValues = {
    name: "",
    mcc: "",
    cpf: "",
    email: "",
  };

  const getCustomerById = (id) => {
    axios
      .get(`http://localhost:8080/api/v1/individual-customers/${id}`, {
        auth: {
          username: "user",
          password: "user",
        },
      })
      .then((response) => {
        if (response.status === 200) {
          formikRef.current.setValues(response.data);
        }
        // TO DO - if status not 200, show error alert
      });
  };

  useEffect(() => {
    console.log("ID", id);
    getCustomerById(id);
  }, []);

  const validations = yup.object().shape({
    name: yup
      .string()
      .required("Campo obrigatório")
      .max(50, "Máximo de 50 caracteres"),
    mcc: yup
      .string()
      .required("Campo obrigatório")
      .min(4, "Precisa ter 4 digitos")
      .max(4, "Precisa ter 4 digitos"),
    cpf: yup
      .string()
      .required("Campo obrigatório")
      .min(11, "Precisa ter 11 digitos")
      .max(11, "Precisa ter 11 digitos"),
    email: yup
      .string()
      .email("Email não é válido")
      .required("Campo obrigatório"),
  });

  const updateCustomer = (values) => {
    values.id = id;
    console.log(values);
    axios
      .put(`http://localhost:8080/api/v1/individual-customers/${id}`, values, {
        auth: {
          username: "user",
          password: "user",
        },
      })
      .then((response) => {
        // TO DO - if status 201 show snackbar that was succesful
        // TO DO - if status not 201, show error alert
        // TO DO - reset values
      });
  };

  return (
    <Box m="20px">
      <Header
        title="Pessoa Física"
        subtitle="Atualizar cliente"
      />

      <Formik
        innerRef={formikRef}
        onSubmit={updateCustomer}
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
                label="Nome"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.name}
                name="name"
                error={!!touched.name && !!errors.name}
                helperText={touched.name && errors.name}
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
                sx={{ gridColumn: "span 2" }}
              />
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="CPF (somente número, sem pontuações)"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.cpf}
                name="cpf"
                error={!!touched.cpf && !!errors.cpf}
                helperText={touched.cpf && errors.cpf}
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
                Atualizar cliente
              </Button>
            </Box>
          </form>
        )}
      </Formik>
    </Box>
  );
};

export default EditIndividualCustomerForm;
