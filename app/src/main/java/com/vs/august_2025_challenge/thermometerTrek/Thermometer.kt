package com.vs.august_2025_challenge.thermometerTrek


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vs.august_2025_challenge.R
import com.vs.august_2025_challenge.ui.theme.Outline
import com.vs.august_2025_challenge.ui.theme.Outline25
import com.vs.august_2025_challenge.ui.theme.Primary
import com.vs.august_2025_challenge.ui.theme.Surface
import com.vs.august_2025_challenge.ui.theme.SurfaceHigher
import com.vs.august_2025_challenge.ui.theme.TextDisabled
import com.vs.august_2025_challenge.ui.theme.TextPrimary
import com.vs.august_2025_challenge.ui.theme.TextSecondary
import com.vs.august_2025_challenge.ui.theme.normal

@Composable
fun ThermometerRoot(
    viewModel: ThermometerViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ThermometerScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun ThermometerScreen(
    state: ThermometerState,
    onAction: (ThermometerAction) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ){paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

            if(state.isInitial) {
                Column(
                    modifier = Modifier.width(360.dp).height(
                        201.dp
                    ).clip(
                        shape = RoundedCornerShape(16.dp)
                    ).background(
                        color = SurfaceHigher
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,

                    ) {

                    Text(
                        text = "Thermometer Treck",
                        style = MaterialTheme.typography.normal.copy(
                            fontSize = 28.sp,
                            lineHeight = 32.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Press Start to begin tracking\ntemperature",
                        color = TextSecondary,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.normal.copy(
                            fontSize = 17.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {
                              onAction(ThermometerAction.OnClickStart)
                        },
                        border = BorderStroke(
                            width = 1.dp,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Outline,
                                    Outline25
                                )
                            )
                        ),
                        modifier = Modifier.height(44.dp).wrapContentWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Start",
                            color = Color.White,
                            style = MaterialTheme.typography.normal.copy(
                                fontSize = 17.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )

                    }


                }
            }else{
                Column(
                    modifier = Modifier.width(370.dp).height(
                        470.dp
                    ).clip(
                        shape = RoundedCornerShape(16.dp)
                    ).background(
                        color = SurfaceHigher
                    ).padding(
                        horizontal = 26.dp,

                    ).padding(
                        top = 32.dp

                    ),



                    ) {

                    Text(
                        text = "Thermometer Treck",
                        style = MaterialTheme.typography.normal.copy(
                            fontSize = 28.sp,
                            lineHeight = 32.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(modifier = Modifier.wrapContentHeight().wrapContentWidth(),
                        horizontalArrangement = Arrangement.spacedBy(3.dp),
                        verticalAlignment = Alignment.CenterVertically

                        ) {
                        Icon(painter = painterResource(id = R.drawable.check_circle),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = if(state.size < 20)TextDisabled else Primary
                        )
                        Text(
                            text = "${state.size}/20",
                            style = MaterialTheme.typography.normal.copy(
                                fontSize = 17.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            color = TextSecondary
                        )

                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(10),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.height(254.dp).width(312.dp),
                        userScrollEnabled = false

                    ) {

                        items(state.tempFlow){ temp ->
                            Row(
                                modifier = Modifier.height(24.dp).width(153.dp),
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                               Icon(
                                   painter = painterResource(id = R.drawable.thermometer_02),
                                   contentDescription = null,
                                   modifier = Modifier.align(Alignment.CenterVertically),
                                   tint = if(temp != null) Primary else TextDisabled
                               )
                                Text(
                                    text = if(temp != null) "$temp °F" else "",
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    style = MaterialTheme.typography.normal.copy(
                                        fontSize = 17.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight.Medium
                                    ),
                                    color = TextPrimary
                                )

                            }

                        }

                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {
                          onAction(ThermometerAction.OnResetClick)
                        },
                        enabled = state.isCompleted,
                        modifier = Modifier.height(44.dp).align(
                            Alignment.CenterHorizontally
                        ),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary,
                            disabledContainerColor = Surface ,

                        ),
                        border = if(state.isCompleted){ BorderStroke(
                            width = 1.dp,
                            brush =Brush.verticalGradient(
                                colors = listOf(
                                    Outline,
                                    Outline25
                                )
                            )
                         )
                        }else null


                        ){
                        Text(
                            text = if(state.isCompleted)"Reset" else "Tracking...",
                            color =  if(state.isCompleted) Color.White else TextDisabled,
                            style = MaterialTheme.typography.normal.copy(
                                fontSize = 17.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )


                    }



                }
            }

        }
    }
}

//@Preview
//@Composable
//private fun Preview() {
//    August_2025_ChallengeTheme {
//        ThermometerScreen(
//            state = ThermometerState(),
//            onAction = {}
//        )
//    }
//}