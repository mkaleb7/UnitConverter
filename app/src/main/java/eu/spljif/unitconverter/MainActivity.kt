package eu.spljif.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.spljif.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpend by remember { mutableStateOf(false) }
    var oExpended by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnits(){
        val inputValueDouble =inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



       Text(text = "Unit converter", style = MaterialTheme.typography.headlineLarge )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange ={
            inputValue = it
            convertUnits()


        },
            label = { Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            //Input Box
            Box {
              Button(onClick = { iExpend = true }) {
                Text(text = inputUnit)
                  Icon(Icons.Default.ArrowDropDown,
                      contentDescription = "Arrow Down")
                
              }
                DropdownMenu(expanded = iExpend, onDismissRequest = { iExpend = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            iExpend = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()


                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {  iExpend = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits() }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {  iExpend = false
                            inputUnit = "feet"
                            conversionFactor.value = 0.3048
                            convertUnits() }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Milimeters") },
                        onClick = {  iExpend = false
                            inputUnit = "Milimeters"
                            conversionFactor.value = 0.01
                            convertUnits() }
                    )
                    
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box {
                Button(onClick = { oExpended = true }) {
                    Text(text = outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = oExpended, onDismissRequest = { oExpended = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            oExpended = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits() }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            oExpended = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.0
                            convertUnits() }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            oExpended = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits() }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Milimeters") },
                        onClick = {
                            oExpended = false
                            outputUnit = "Milimeters"
                            oConversionFactor.value = 0.001
                            convertUnits() }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Result Text
        Text(text = "Result: $outputValue $outputUnit" ,
              style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
