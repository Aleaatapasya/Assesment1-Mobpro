package com.aleaatapasya0002.assesment1.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aleaatapasya0002.assesment1.R
import com.aleaatapasya0002.assesment1.navigation.Screen
import com.aleaatapasya0002.assesment1.ui.theme.Assesment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    OverflowMenu(navController)
                }
            )
        }
    ) { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.gambar_intro)

    val satuanBerat =
        arrayOf(R.string.kg, R.string.hg, R.string.dag, R.string.gram, R.string.dg, R.string.cg, R.string.mg)

    val selectedAwal = remember { mutableIntStateOf(satuanBerat[0]) }
    val selectedAkhir = remember { mutableIntStateOf(satuanBerat[1]) }

    val expandedAwal = remember { mutableStateOf(false) }
    val expandedAkhir = remember { mutableStateOf(false) }

    var berat by remember { mutableStateOf("") }
    var beratError by remember { mutableStateOf(false) }

    var satuanAkhirTerpilih by remember { mutableIntStateOf(selectedAkhir.intValue) }
    var konversi by remember { mutableFloatStateOf(0f) }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
        )
        Image(
            painter = image,
            contentDescription = stringResource(id = R.string.gambar),
            modifier = modifier
                .size(180.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 1.dp)
                    .weight(1f)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expandedAwal.value,
                    onExpandedChange = {
                        expandedAwal.value = !expandedAwal.value
                    }
                ) {
                    TextField(
                        value = stringResource(selectedAwal.intValue),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(text = stringResource(R.string.satuan_awal)) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedAwal.value)
                        },
                        textStyle = TextStyle(fontSize = 15.sp),
                        singleLine = true,
                        modifier = Modifier
                            .menuAnchor()
                            .width(180.dp)
                    )
                    ExposedDropdownMenu(
                        expanded = expandedAwal.value,
                        onDismissRequest = { expandedAwal.value = false }
                    ) {
                        satuanBerat.forEach { resId ->
                            DropdownMenuItem(
                                text = { Text(text = stringResource(resId)) },
                                onClick = {
                                    selectedAwal.intValue = resId
                                    expandedAwal.value = false
                                }
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 1.dp)
                    .weight(1f)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expandedAkhir.value,
                    onExpandedChange = {
                        expandedAkhir.value = !expandedAkhir.value
                    }
                ) {
                    TextField(
                        value = stringResource(selectedAkhir.intValue),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(text = stringResource(R.string.satuan_akhir)) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedAkhir.value)
                        },
                        textStyle = TextStyle(fontSize = 15.sp),
                        singleLine = true,
                        modifier = Modifier
                            .menuAnchor()
                            .width(180.dp)
                    )
                    ExposedDropdownMenu(
                        expanded = expandedAkhir.value,
                        onDismissRequest = { expandedAkhir.value = false }
                    ) {
                        satuanBerat.forEach { resId ->
                            DropdownMenuItem(
                                text = { Text(text = stringResource(resId)) },
                                onClick = {
                                    selectedAkhir.intValue = resId
                                    expandedAkhir.value = false
                                }
                            )
                        }
                    }
                }
            }
        }
            OutlinedTextField(
                value = berat,
                onValueChange = { berat = it},
                label = {Text(text = stringResource(R.string.input))},
                trailingIcon = {IconPicker(beratError, getInitial(stringResource( selectedAwal.intValue))) },
                supportingText = { ErrorHint(beratError) },
                isError = beratError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
        Button(
            onClick = {
                beratError = (berat == "" || berat == "0")
                if (beratError) return@Button

                konversi = hitungKonversi(berat.toFloat(), selectedAwal.intValue, selectedAkhir.intValue)
                satuanAkhirTerpilih = selectedAkhir.intValue
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp)
        ) {
            Text(text = stringResource(R.string.button))
        }
        if (!beratError && konversi > 0){
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            val hasilFormat =
                if (konversi < 0.01f){
                    "%.6f".format(konversi)
            }
                else {
                "%.2f".format(konversi)
                }

            Text(
                text = stringResource(R.string.hasil, hasilFormat, stringResource(satuanAkhirTerpilih)),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 15.dp)
            )
            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(
                            R.string.bagikan_template,
                            context.getString(selectedAwal.intValue),
                            context.getString(selectedAkhir.intValue),
                            berat,
                            hasilFormat
                        )
                    )
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.bagikan))
            }
        }
    }
}

@Composable
fun OverflowMenu(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(10.dp)
    ){
        IconButton(onClick = {expanded = !expanded}) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.menu_overflow))
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false}
        ) {
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = stringResource(R.string.tentang_aplikasi),
                        tint = MaterialTheme.colorScheme.primary
                    ) },
                        text = {Text(text = stringResource(R.string.tentang_aplikasi))},
                        onClick = {
                            expanded = false
                            navController.navigate(Screen.About.route)
                        }
                    )
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.help),
                                modifier = Modifier.size(22.dp),
                                contentDescription = stringResource(R.string.tutorial),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        text = {Text(text = stringResource(R.string.tutorial))},
                        onClick = {
                            expanded = false
                            navController.navigate(Screen.Tutorial.route)
                        }
                     )

        }
    }
}

private fun hitungKonversi(berat:Float, satuanAwal:Int, satuanAkhir:Int): Float{
    val konversiSatuan = mapOf(
        R.string.kg to 1_000_000f,
        R.string.hg to 100_000f,
        R.string.dag to 10_000f,
        R.string.gram to 1_000f,
        R.string.dg to 100f,
        R.string.cg to 10f,
        R.string.mg to 1f,
    )

    val hitungAwal = konversiSatuan[satuanAwal] ?: 1f
    val hitungAkhir = konversiSatuan[satuanAkhir] ?: 1f

    return (berat * hitungAwal) / hitungAkhir
}

private fun getInitial(unit: String): String{
    return unit.takeLast(4)
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }
    else{
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}

private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    Assesment1Theme {
        MainScreen(rememberNavController())
    }
}