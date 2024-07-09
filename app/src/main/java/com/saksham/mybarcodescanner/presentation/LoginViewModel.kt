package com.saksham.mybarcodescanner.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {
    fun login(employeeId: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("employees").document(employeeId).get().await()
                if (document.exists()) {
                    val storedPassword = document.getString("password")
                    if (storedPassword == password) {
                        onSuccess()
                    } else {
                        onError("Invalid password")
                    }
                } else {
                    onError("Employee not found")
                }
            } catch (e: Exception) {
                onError(e.message ?: "An error occurred")
            }
        }
    }
}