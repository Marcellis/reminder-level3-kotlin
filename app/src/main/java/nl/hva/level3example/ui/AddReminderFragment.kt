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
import nl.hva.level3example.R
import nl.hva.level3example.databinding.FragmentAddReminderBinding
import nl.hva.level3example.model.Reminder

const val REMINDER_KEY = "reminder"

class AddReminderFragment : Fragment() {
    private var _binding: FragmentAddReminderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView. Nullability is handled by Android SDK (!!)
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddReminderBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddReminder.setOnClickListener {
            onAddReminder()
        }
    }

    private fun onAddReminder() {
        val reminderText = binding.tilReminderText.text.toString()

        if (reminderText.isNotBlank()) {
            //create model to send back
            val reminder = Reminder(reminderText)

            //hide keyboard just before popping
            hideKeyboard()

            //set the data on the previousBackStackEntry, this is the RemindersFragment
            findNavController().previousBackStackEntry?.savedStateHandle?.set(REMINDER_KEY, reminder)

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

    /**
     * Make binding null when fragment get's destroyed to prevent mem leaks
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
