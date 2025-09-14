package com.example.unitconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConvertorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(innerPadding = innerPadding)
                }
            }
        }
    }
}

@Composable
fun UnitConverter(innerPadding: PaddingValues) {
    // simple state for the TextField
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf(value = "") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.00) }


    fun convertUnit() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result =
            (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0

        outputValue = result.toString()
    }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,


        ) {
        Text(
            text = "Unit Converter", style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnit()
            },
            label = { Text("Enter value") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            // val context = LocalContext.current
            //input box
            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Centimeters"
                        conversionFactor.doubleValue = 0.01
                        convertUnit()
                    })

                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Meters"
                        conversionFactor.doubleValue = 1.0
                        convertUnit()
                    })

                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        conversionFactor.doubleValue = 0.3048
                        convertUnit()
                    })

                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Millimeters"
                        conversionFactor.doubleValue = 0.001
                        convertUnit()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //output box
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeters"
                        conversionFactor.doubleValue = 0.01
                        convertUnit()
                    })

                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Meters"
                        conversionFactor.doubleValue = 1.0
                        convertUnit()
                    })

                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        conversionFactor.doubleValue = 0.3048
                        convertUnit()
                    })

                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Millimeters"
                        conversionFactor.doubleValue = 0.001
                        convertUnit()
                    })
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Result:$outputValue $outputUnit", style = MaterialTheme.typography.headlineMedium)

    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConvertorTheme {
        UnitConverter(innerPadding = PaddingValues(0.dp))
    }
}
