package uoc.cbonache.tfg.ui.shippingList

import android.content.Context
import android.content.Intent
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import uoc.cbonache.tfg.R
import uoc.cbonache.tfg.ui.Navigator
import uoc.cbonache.tfg.ui.base.BaseActivity
import uoc.cbonache.tfg.ui.model.ShippingViewEntity
import kotlinx.android.synthetic.main.activity_shipping_list.*
import com.google.zxing.integration.android.IntentIntegrator
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * @author cbonache
 */
class ShippingListActivity : BaseActivity(), ShippingListView {

    companion object {

        @JvmStatic
        fun getIntent(context: Context): Intent {
            return Intent(context, ShippingListActivity::class.java)
        }
    }

    @Inject lateinit var presenter: ShippingListPresenter
    @Inject lateinit var navigator: Navigator
    @Inject lateinit var adapter: ShippingAdapter
    private val VERTICAL_ITEM_SPACE = 8


    override fun onRequestLayout(): Int {
        return R.layout.activity_shipping_list
    }

    override fun onViewLoaded() {

        setUpActionBar()
        showTitle()

        val layoutManager = LinearLayoutManager(this)
        shippingList.layoutManager = layoutManager
        shippingList.adapter = adapter
        shippingList.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))

        presenter.onStart()

    }

    private fun setUpActionBar() {

        supportActionBar?.apply {
            title = getString(R.string.shipping_list)
            setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun showTitle() {
        var today = Date()
        titleList.text = getString(R.string.titleList) + " " + SimpleDateFormat("dd/MM", Locale.getDefault()).format(today)
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

    override fun showShippingList(shippingList: List<ShippingViewEntity>) {
        adapter.shippingList = shippingList
    }

    override fun showNoShippingsWaring() {
        shippingList.visibility = View.GONE
        warningMessage.visibility = View.VISIBLE
    }

    override fun dismissNoShippingsWarning() {
        shippingList.visibility = View.VISIBLE
        warningMessage.visibility = View.GONE
    }

    override fun navigateToShippingDetail(code: String) {
        navigator.navigateToShippingDetail(this, code)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.shipping_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_qr -> presenter.onClickQR()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun scanQR() {
        IntentIntegrator(this).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                navigator.navigateToShippingDetail(this,result.contents)
//                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}
