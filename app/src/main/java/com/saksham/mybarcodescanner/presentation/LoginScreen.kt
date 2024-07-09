package com.saksham.mybarcodescanner.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.saksham.mybarcodescanner.R

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {
    var employeeId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.divueens_logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // App Name
        Text(
            "Warehouse App",
            style = MaterialTheme.typography.h4,
            color = Color(0xFFE91E63) // Pink color
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Login Text
        Text(
            "LOG IN",
            style = MaterialTheme.typography.h5,
            color = Color(0xFFE91E63)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Employee ID input
        OutlinedTextField(
            value = employeeId,
            onValueChange = { employeeId = it },
            label = { Text("Employee ID") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFE91E63),
                focusedLabelColor = Color(0xFFE91E63)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password input
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFE91E63),
                focusedLabelColor = Color(0xFFE91E63)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Forgot password link
        Text(
            "Forgot Password?",
            color = Color(0xFFE91E63),
            modifier = Modifier
                .align(Alignment.End)
                .clickable { /* Handle forgot password */ }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Login button
        Button(
            onClick = {
                viewModel.login(
                    employeeId,
                    password,
                    onSuccess = onLoginSuccess,
                    onError = { error = it }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE91E63))
        ) {
            Text("Log in", color = Color.White)
        }

        // Error message
        error?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(it, color = Color.Red)
        }
    }
}