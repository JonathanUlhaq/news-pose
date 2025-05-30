package id.prj.fl.newspose.core.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import id.prj.fl.newspose.R
import id.prj.fl.newspose.core.network.ErrorStatus

@Stable
class NewsDialogState {
    private val dialogError = mutableStateListOf<ErrorStatus>()
    val currentErrorDialog by derivedStateOf {
        dialogError.firstOrNull()
    }

    fun addError(error: ErrorStatus) {
        dialogError.add(error)
    }

    internal fun dismissDialog() {
        dialogError.clear()
    }
}

@Composable
fun rememberNewsDialogState(): NewsDialogState {
    return remember {
        NewsDialogState()
    }
}

@Composable
fun NewsDialog(
    state: NewsDialogState,
) {

    when(val error = state.currentErrorDialog) {
        is ErrorStatus.UserLimitAccess -> {
            NewsDialog(
                isVisible = true,
                title = stringResource(R.string.error_limit_access),
                desc = stringResource(R.string.error_limit_access_desc),
                onDismissRequest = state::dismissDialog
            )
        }
        is ErrorStatus.TooManyRequest -> {
            NewsDialog(
                isVisible = true,
                title = stringResource(R.string.error_please_wait),
                desc = stringResource(R.string.error_too_many_request_desc),
                onDismissRequest = state::dismissDialog
            )
        }
        is ErrorStatus.Forbidden -> {
            NewsDialog(
                isVisible = true,
                title = stringResource(R.string.error_forbidden_title),
                desc = stringResource(R.string.error_forbidden_desc),
                onDismissRequest = state::dismissDialog
            )
        }
       is ErrorStatus.GeneralError -> {
           NewsDialog(
               isVisible = true,
               title = "Error",
               desc = error.message,
               onDismissRequest = state::dismissDialog
           )
        }
        else -> {
            // not used
        }
    }
}

