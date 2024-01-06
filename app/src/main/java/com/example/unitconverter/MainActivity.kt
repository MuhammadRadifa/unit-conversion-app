package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember{ mutableStateOf("0") }
    var outputValue by remember { mutableStateOf("0.0") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by  remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember{ mutableStateOf(0.01) }
    val oConversionFactor = remember { mutableStateOf(1.00) }


    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

           Text(text = "Unit Conversion",style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = inputValue,
                onValueChange = {
                inputValue = it
                    convertUnit()
            },
                label = { Text("ENTER VALUE") })
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "From", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))
            Box{
                Button(onClick = { iExpanded = true}) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(onClick = {
                        iExpanded = false
                        inputUnit = "Centimeters"
                        conversionFactor.value = 0.01
                        convertUnit()
                    }) {
                        Text(text = "Centimeters")
                    }
                    DropdownMenuItem(onClick = {
                        iExpanded = false
                        inputUnit = "Meters"
                        conversionFactor.value = 1.0
                        convertUnit()
                    }) {
                        Text(text = "Meters")
                    }
                    DropdownMenuItem(onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        conversionFactor.value = 0.3048
                        convertUnit()
                    }) {
                        Text(text = "Feet")
                    }
                    DropdownMenuItem(onClick = {
                        iExpanded = false
                        inputUnit = "Milimeters"
                        conversionFactor.value = 0.001
                        convertUnit()
                    }) {
                        Text(text = "Milimeters")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "To", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))
            Box{
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(onClick = {
                        oExpanded = false
                        outputUnit = "Centimeters"
                        oConversionFactor.value = 0.01
                        convertUnit()
                    }) {
                        Text(text = "Centimeters")
                    }
                    DropdownMenuItem(onClick = {
                        oExpanded = false
                        outputUnit = "Meters"
                        oConversionFactor.value = 1.0
                        convertUnit()
                    }) {
                        Text(text = "Meters")
                    }
                    DropdownMenuItem(onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        oConversionFactor.value = 0.3048
                        convertUnit()
                    }) {
                        Text(text = "Feet")
                    }
                    DropdownMenuItem(onClick = {
                        oExpanded = false
                        outputUnit = "Milimeters"
                        oConversionFactor.value = 0.001
                        convertUnit()
                    }) {
                        Text(text = "Milimeters")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "$outputValue $outputUnit", style = MaterialTheme.typography.h4)

    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}