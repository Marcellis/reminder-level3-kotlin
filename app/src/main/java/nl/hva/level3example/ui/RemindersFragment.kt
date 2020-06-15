package nl.hva.level3example.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_reminders.*
import nl.hva.level3example.R
import nl.hva.level3example.model.Reminder

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RemindersFragment : Fragment() {

    //populate some test data in Reminders list
    private var reminders: ArrayList<Reminder> = arrayListOf(
        Reminder("Wash horses"),
        Reminder("Feed squirrels")
    )

    private lateinit var reminderAdapter: ReminderAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminders, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRv()
        observeAddReminderResult()
    }

    private fun initRv() {

        reminderAdapter = ReminderAdapter(reminders)
        viewManager = LinearLayoutManager(activity)

        rvReminder.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = reminderAdapter
        }
    }

    private fun observeAddReminderResult() {
        setFragmentResultListener(REQ_REMINDER_KEY) { key, bundle ->
           bundle.getString(BUNDLE_REMINDER_KEY)?.let {
                val reminder = Reminder(it)

                reminders.add(reminder)
                reminderAdapter.notifyDataSetChanged()
            } ?: Log.e("ReminderFragment", "Request triggered, but empty reminder text!")

        }
    }

}
