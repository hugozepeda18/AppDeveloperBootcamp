import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet } from 'react-native';
import axios from 'axios';
const App = () => {
    const [inputText, setInputText] = useState('');
    const [generatedText, setGeneratedText] = useState('');
    const generateText = async () => {
        const response = await axios.post(
            'https://api-inference.huggingface.co/models/google/gemma-2-2b-it',
            { 
                inputs: inputText 
            },
            { 
                headers: { 
                    Authorization: "Bearer hf_***",
				    "Content-Type": "application/json",
                }
            }
        );
        setGeneratedText(response.data.generated_text);
    };
    return (
    <View style={styles.container}>
    <TextInput
    style={styles.input}
    placeholder="Escribe algo..."
    onChangeText={setInputText}
    value={inputText}
    />
    <Button title="Generar Texto"
    onPress={generateText} />
    {generatedText && <Text
    style={styles.output}>{generatedText}</Text>}
    </View>
    );
}