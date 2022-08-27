package com.cinema.emovie.common

import android.content.Context
import android.view.View
import com.cinema.emovie.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import retrofit2.HttpException
import java.io.IOException

object ErrorHandler {

    private var currentMessage: String? = null
    private var currentViewId: Int? = null

    private fun getErrorMessage(e: Exception, context: Context) = when (e) {
        is HttpException -> {
            context.getString(R.string.http_exception_message)
        }
        is IOException -> {
            context.getString(R.string.io_exception_message)
        }
        else -> {
            context.getString(R.string.other_exception_message)
        }
    }

    private fun validateShowMessage(newMessage: String, viewId: Int) =
        when {
            currentMessage == null -> true
            viewId != currentViewId -> true
            else -> newMessage != currentMessage
        }


    fun showErrorMessage(exception: Exception, context: Context, view: View) {
        val errorMessage = getErrorMessage(exception, context)
        val showMessage = validateShowMessage(errorMessage, view.id)
        if (showMessage) {
            currentMessage = errorMessage
            currentViewId = view.id
            Snackbar.make(
                context,
                view,
                errorMessage,
                Snackbar.LENGTH_LONG
            ).addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    currentMessage = null
                    currentViewId = null
                    super.onDismissed(transientBottomBar, event)
                }
            }).show()
        }
    }

}