package id.prj.fl.newspose.features.explore.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import id.prj.fl.newspose.ui.theme.NewsPoseTheme
import id.prj.fl.newspose.ui.theme.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.prj.fl.newspose.R
import id.prj.fl.newspose.ui.theme.AliceBlue
import id.prj.fl.newspose.ui.theme.BlueOpal
import id.prj.fl.newspose.ui.theme.DarkBlue
import id.prj.fl.newspose.ui.theme.newsFontFamily

@Composable
fun ExploreSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    onFocus: () -> Unit,
    onDelete: () -> Unit,
    onDone: (String) -> Unit,
) {

    val keyboardOption = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            errorBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            errorContainerColor = AliceBlue,
            focusedContainerColor = AliceBlue,
            unfocusedContainerColor = AliceBlue,
            cursorColor = DarkBlue,
            focusedTextColor = DarkBlue,
            unfocusedTextColor = BlueOpal
        ),
        prefix = {
            Box(
                modifier = Modifier
                    .padding(end = 12.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_unselect_explore),
                    contentDescription = "Search Icon",
                    modifier = Modifier
                        .size(12.dp)
                )
            }
        },
        suffix = {
            if (value.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .padding(start = 12.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_delete_search_input),
                        contentDescription = "Delete Icon",
                        modifier = Modifier
                            .size(14.dp)
                            .clickable { if (value.isNotEmpty()) onDelete.invoke() }
                    )
                }
            }
        },
        textStyle = TextStyle(
            fontFamily = newsFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        ),
        keyboardActions = KeyboardActions(onDone = {
            keyboardOption?.hide()
            onDone.invoke(value)
        }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                if (it.isFocused) onFocus.invoke()
            },
        placeholder = {
            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = "Search News",
            )
        }
    )
}

@Preview
@Composable
fun ExploreSearchFieldPreview() {
    NewsPoseTheme {
        Surface(color = White) {
            Box(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                ExploreSearchField(
                    value = "",
                    onValueChange = {},
                    onDelete = {},
                    onDone = {},
                    onFocus = {}
                )
            }
        }
    }
}