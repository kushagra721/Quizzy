package com.quizzy.`in`.userInterface.login

import android.R.attr.maxHeight
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.quizzy.`in`.R
import com.quizzy.`in`.util.BottomCutoutShape

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    val systemUiController = rememberSystemUiController()

    val keyboardInsets = WindowInsets.ime
    val density = LocalDensity.current
    val isKeyboardOpen by remember(keyboardInsets, density) {
        derivedStateOf {
            keyboardInsets.getBottom(density) > 0
        }
    }


    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Black,
            darkIcons = false
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF000000))
            .padding(bottom = 10.dp)

            .systemBarsPadding()
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        )
        {

            if (!isKeyboardOpen) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                    Image(
                        painter = painterResource(id = R.drawable.ellipse_3),
                        contentDescription = null,
                        modifier = Modifier
                            .size(46.dp)
                            //  .align(Alignment.TopStart)
                            .offset(x = -15.dp, y = 0.dp)
                        // .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.weight(1.3f))
                    Image(
                        painter = painterResource(id = R.drawable.pw_logo_3),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                        //  .align(Alignment.TopStart)
                        // .offset(x = 40.dp, y = 40.dp)
                        // .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.ellipse_5),
                        contentDescription = null,
                        modifier = Modifier
                            .size(46.dp)
                            //  .align(Alignment.TopStart)
                            .offset(x = 5.dp, y = 0.dp)
                        // .clip(CircleShape)
                    )


                }
            }



            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ellipse_4),
                    contentDescription = null,
                    modifier = Modifier
                        .size(14.dp)
                    //  .align(Alignment.TopStart)
                    // .offset(x = 40.dp, y = 40.dp)
                    // .clip(CircleShape)
                )

                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.topcreative),
                    contentDescription = null,
                    modifier = Modifier
                        .size(if (!isKeyboardOpen) 270.dp else 150.dp)
                    //  .align(Alignment.TopStart)
                    // .offset(x = 40.dp, y = 40.dp)
                    // .clip(CircleShape)
                )

                Spacer(modifier = Modifier.weight(1f))


            }


            if (!isKeyboardOpen) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                    Image(
                        painter = painterResource(id = R.drawable.pi),
                        contentDescription = null,
                        modifier = Modifier
                            .size(26.dp)
                        //  .align(Alignment.TopStart)
                        // .offset(x = 40.dp, y = 40.dp)
                        // .clip(CircleShape)
                    )



                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.ellipse_6),
                        contentDescription = null,
                        modifier = Modifier
                            .size(14.dp)
                        //  .align(Alignment.TopStart)
                        // .offset(x = 40.dp, y = 40.dp)
                        // .clip(CircleShape)
                    )


                }
            }



            state.error?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }

        Box(
            modifier = Modifier
                .background(Color.Black)
                .align(Alignment.BottomCenter)
        )
        {

            BottomLoginCard(state, viewModel, onLoginSuccess)

        }

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f))
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Signing you in…",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }


    }


}


@Composable
fun BottomLoginCard(
    state: LoginUiState,
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit

) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = Color.White, shape = RoundedCornerShape(30.dp))
            .padding(horizontal = 20.dp, vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Let’s Get you Signed in",
            //style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))


        OutlinedTextField(
            value = state.username,
            onValueChange = viewModel::onUsernameChange,
            placeholder = { Text("School ID") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFE0E0E0),
                unfocusedBorderColor = Color(0xFFE0E0E0)
            )
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChange,
            placeholder = { Text("Student ID") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFE0E0E0),
                unfocusedBorderColor = Color(0xFFE0E0E0)
            )
        )

        Spacer(Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = { viewModel.onLoginClick(
                    schoolId = state.username,
                    studentId = state.password
                ) {
                    onLoginSuccess()
                }

                 },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),

                shape = RoundedCornerShape(20.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),

                ) {
                Text("Sign in", fontSize = 16.sp)
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPrdfbdveviewww__() {
    MaterialTheme {
        LoginScreen(onLoginSuccess = {})
    }
}