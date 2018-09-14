package com.babynames.names.presentation.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.babynames.core.presentation.getApplicationComponent
import com.babynames.names.R
import com.babynames.names.domain.entities.Name
import com.babynames.names.presentation.adapters.SwipeNamesAdapter
import com.babynames.names.presentation.components.DaggerNamesComponent
import com.babynames.names.presentation.components.NamesComponent
import com.babynames.names.presentation.modules.NamesModule
import com.babynames.names.presentation.presenters.abstractions.NamesPresenter
import com.babynames.names.presentation.viewModels.NamesViewModel
import com.ia.mchaveza.kotlin_library.SharedPreferencesManager
import kotlinx.android.synthetic.main.fragment_names.*
import link.fls.swipestack.SwipeStack
import org.jetbrains.anko.okButton
import org.jetbrains.anko.support.v4.alert
import javax.inject.Inject

class NamesFragment : Fragment(), NamesViewModel, SwipeStack.SwipeStackListener {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Inject
    lateinit var namesPresenter: NamesPresenter

    private val namesComponent: NamesComponent by lazy {
        DaggerNamesComponent.builder()
                .applicationComponent(this.context!!.getApplicationComponent())
                .namesModule(NamesModule())
                .build()
    }

    private val swipeAdapter: SwipeNamesAdapter by lazy {
        SwipeNamesAdapter()
    }

    private val GENDER_SELECTED = "gender"

    companion object {
        fun newInstance(): NamesFragment {
            return NamesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_names, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.fragment_names_title)

        setHasOptionsMenu(true)

        this.namesComponent.inject(this)

        this.namesPresenter.bind(this)

        this.namesPresenter.getNamesList("GetNames")

        this.swipe_names_list.setListener(this)
        this.swipe_names_list.adapter = swipeAdapter

        this.animation_empty_names.setAnimation(R.raw.empty_status)
        this.animation_empty_names.playAnimation()

    }


    override fun onDetach() {
        super.onDetach()
        this.namesPresenter.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.names_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.reload_names -> {
                this.linear_empty_names.visibility = View.GONE
                this.swipe_names_list.resetStack()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onGetNamesSuccess(namesList: List<Name>) {
        val names = mutableListOf<Name>()

        if (sharedPreferencesManager.getSharedPreference(GENDER_SELECTED, "") == "unisex") {
            swipeAdapter.updateNamesList(namesList)
        } else {
            for (name in namesList) {
                if (name.gender == "unisex" || name.gender == sharedPreferencesManager.getSharedPreference(GENDER_SELECTED, ""))
                    names.add(name)
            }

            swipeAdapter.updateNamesList(names)
        }

        this.swipe_names_list.resetStack()
    }

    override fun displayError(errorMessage: String) {

    }

    override fun displayError(errorMessage: Int) {
        alert {
            message = getString(errorMessage)
            okButton { }
        }.show()
    }

    override fun setProgressVisibility(visibility: Int) {
        this.circular_progress_names.visibility = visibility
    }

    override fun onViewSwipedToLeft(position: Int) {
        //Nothing to do
    }

    override fun onViewSwipedToRight(position: Int) {
        //Save into DB
    }

    override fun onStackEmpty() {
        this.linear_empty_names.visibility = View.VISIBLE
    }
}
