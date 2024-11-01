import React, { useState } from 'react';
import { View, TextInput, Button, Text, StyleSheet } from 'react-native';
import axios from 'axios';
import { Formik } from 'formik';
import * as Yup from 'yup';

const API_URL = 'https://api.huggingface.co/generate';

const MyComponent = () => {
  const [generatedText, setGeneratedText] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleTextGeneration = async (values) => {
    setLoading(true);
    setError(null);
    setGeneratedText('');

    try {
      const response = await axios.post(API_URL, {
        prompt: values.prompt,
      }, {
        headers: { Authorization: `Bearer YOUR_HUGGING_FACE_API_KEY` },
      });

      setGeneratedText(response.data.generated_text);
    } catch (err) {
      setError('Error al generar el texto. Inténtalo de nuevo.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <View style={styles.container}>
      <Formik
        initialValues={{ prompt: '' }}
        validationSchema={Yup.object({
          prompt: Yup.string()
            .max(100, 'El texto no puede superar los 100 caracteres')
            .required('Requerido'),
        })}
        onSubmit={handleTextGeneration}
      >
        {formik => (
          <View>
            <TextInput
              style={styles.input}
              placeholder="Escribe una frase inicial"
              onChangeText={formik.handleChange('prompt')}
              onBlur={formik.handleBlur('prompt')}
              value={formik.values.prompt}
            />
            {formik.touched.prompt && formik.errors.prompt ? (
              <Text style={styles.errorText}>{formik.errors.prompt}</Text>
            ) : null}

            <Button title="Generar texto" onPress={formik.handleSubmit} />
          </View>
        )}
      </Formik>

      {loading && <Text>Cargando...</Text>}
      {error && <Text style={styles.errorText}>{error}</Text>}
      {generatedText && <Text style={styles.resultText}>{generatedText}</Text>}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    justifyContent: 'center',
  },
  input: {
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    marginBottom: 12,
    paddingHorizontal: 8,
  },
  errorText: {
    color: 'red',
    marginBottom: 12,
  },
  resultText: {
    marginTop: 20,
    fontSize: 16,
  },
});

export default MyComponent;
