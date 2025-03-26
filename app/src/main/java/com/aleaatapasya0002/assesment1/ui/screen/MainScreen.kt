package com.aleaatapasya0002.assesment1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aleaatapasya0002.assesment1.R
import com.aleaatapasya0002.assesment1.ui.theme.Assesment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
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
        val image = painterResource(R.drawable.gambar_intro)
        Image(
            painter = image,
            contentDescription = stringResource(id = R.string.gambar),
            modifier = modifier
                .size(180.dp)
        )
        val satuanBerat =
            arrayOf(R.string.kg, R.string.hg, R.string.dag, R.string.gram, R.string.dg, R.string.cg, R.string.mg)
        val selectedAwal = remember { mutableIntStateOf(satuanBerat[0]) }
        val selectedAkhir = remember { mutableIntStateOf(satuanBerat[1]) }
        val expandedAwal = remember { mutableStateOf(false) }
        val expandedAkhir = remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 3.dp)
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
                        modifier = Modifier.menuAnchor()
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
                    .padding(end = 3.dp)
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
                        modifier = Modifier.menuAnchor()
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
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    Assesment1Theme {
        MainScreen()
    }
}