package nl.hva.level3example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nl.hva.level3example.databinding.FragmentRemindersBinding
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

    private lateinit var recyclerView: RecyclerView
    private lateinit var reminderAdapter: ReminderAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
//    private val args: RemindersFragmentArgs by navArgs()

    private var _binding: FragmentRemindersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView. Nullability is handled by Android SDK (!!)
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRemindersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRv()
        observeAddReminderResult()
    }

    private fun initRv() {
        recyclerView = binding.rvReminder


        reminderAdapter = ReminderAdapter(reminders)
        viewManager = LinearLayoutManager(activity)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = reminderAdapter
        }
    }

    private fun observeAddReminderResult() {
        //get the savedState, this time on the currentBackStackEntry
        val savedStateHandle = findNavController().currentBackStackEntry?.savedStateHandle

        savedStateHandle?.
            getLiveData<Reminder>(REMINDER_KEY)?.observe(viewLifecycleOwner, Observer { reminder ->
                reminders.add(reminder)
                reminderAdapter.notifyDataSetChanged()
        })

        //remove it after it's been added to the listview, prevents items being added twice
        savedStateHandle?.remove<Reminder>(REMINDER_KEY)
    }

    /**
     * Make binding null when fragment get's destroyed to prevent mem leaks
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
