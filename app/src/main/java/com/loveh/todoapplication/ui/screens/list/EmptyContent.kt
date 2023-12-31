package com.loveh.todoapplication.ui.screens.list

import com.loveh.todoapplication.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loveh.todoapplication.ui.theme.MediumGray
import com.loveh.todoapplication.ui.theme.topAppBarIconColor

@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            // Size = 120 width and height
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.ic_sad_face),
            contentDescription = "Sad face icon",
            tint = MediumGray
        )
        Text(
            text = "No tasks created yet.",
            color = MediumGray,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize
            )
    }
}

@Composable
@Preview
fun emptyContentPreview() {
    EmptyContent()
}