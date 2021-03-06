package at.roteskreuz.stopcorona.screens.dashboard.changelog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import at.roteskreuz.stopcorona.R
import at.roteskreuz.stopcorona.constants.VERSION_NAME
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Screen for bottomSheetDialog that displays changelog information.
 */
class ChangelogFragment : BottomSheetDialogFragment() {

    private val viewModel: ChangelogViewModel by viewModel()

    private val controller: ChangelogController by lazy {
        ChangelogController(
            context = requireContext(),
            onCtaClick = { dismiss() }
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_changelog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(contentRecyclerView) {
            setController(controller)
        }

        viewModel.getChangelogForVersion(VERSION_NAME)?.let {
            controller.setData(it)
        }
    }
}

fun Fragment.showChangelogBottomSheetFragment() {
    ChangelogFragment().show(requireFragmentManager(), ChangelogFragment::class.java.name)
}
