import React from 'react';
import { Formik } from 'formik';
import * as Yup from 'yup';

const Formulario = () => {
  return (
    <Formik
      initialValues={{ name: '', email: '', password: '' }}
      validationSchema={Yup.object({
        name: Yup.string()
          .max(50, 'El nombre no puede superar los 50 caracteres')
          .required('Requerido'),
        email: Yup.string()
          .email('Email inválido')
          .required('Requerido'),
        password: Yup.string()
          .min(8, 'La contraseña debe tener al menos 8 caracteres')
          .required('Requerido'),
      })}
      onSubmit={(values) => {
        // Lógica de envío
        console.log(values);
      }}
    >
      {formik => (
        <form onSubmit={formik.handleSubmit}>
          <div>
            <input
              name="name"
              type="text"
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              value={formik.values.name}
              placeholder="Nombre"
            />
            {formik.touched.name && formik.errors.name ? (
              <div>{formik.errors.name}</div>
            ) : null}
          </div>

          <div>
            <input
              name="email"
              type="email"
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              value={formik.values.email}
              placeholder="Email"
            />
            {formik.touched.email && formik.errors.email ? (
              <div>{formik.errors.email}</div>
            ) : null}
          </div>

          <div>
            <input
              name="password"
              type="password"
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              value={formik.values.password}
              placeholder="Contraseña"
            />
            {formik.touched.password && formik.errors.password ? (
              <div>{formik.errors.password}</div>
            ) : null}
          </div>

          <button type="submit">Enviar</button>
        </form>
      )}
    </Formik>
  );
};

export default Formulario;
