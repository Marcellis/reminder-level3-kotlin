package nl.hva.level3example.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_reminder.*
import nl.hva.level3example.R
import nl.hva.level3example.databinding.FragmentAddReminderBinding
import nl.hva.level3example.model.Reminder

const val REMINDER_KEY = "reminder"

class AddReminderFragment : Fragment() {
    private var _binding: FragmentAddReminderBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_reminder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddReminder.setOnClickListener {
            onAddReminder()
        }
    }

    private fun onAddReminder() {
        val reminderText = tilReminderText.text.toString()

        if (reminderText.isNotBlank()) {

            //extra, not mandatory in tutorial
            hideKeyboard()

            //set the data on the previousBackStackEntry, this is the RemindersFragment
            findNavController().previousBackStackEntry?.savedStateHandle?.set(REMINDER_KEY, reminderText)

            //"pop" the backstack, this means we destroy this fragment and go back to the RemindersFragment
            findNavController().popBackStack()

        } else {
            Toast.makeText(
                activity,
                R.string.not_valid_reminder, Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Would be very cool to make this an extension function of the Fragment class! Try it!
     */
    private fun hideKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // Check if no view has focus
        val currentFocusedView = activity?.currentFocus

        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

}
