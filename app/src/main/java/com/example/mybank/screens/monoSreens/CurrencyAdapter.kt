import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mybank.screens.monoSreens.CurrencyItem


class CurrencyAdapter(context: Context, countryList: ArrayList<CurrencyItem>) :
    ArrayAdapter<CurrencyItem>(context, 0, countryList) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertViewVar = convertView
        if (convertViewVar == null) {
            convertViewVar = LayoutInflater.from(context).inflate(
                com.example.mybank.R.layout.dropdown_item, parent, false
            )
        }
        val textViewName: TextView = convertView!!.findViewById(com.example.mybank.R.id.tvCodOfCurrencyDropDown)

        val currentItem: CurrencyItem? = getItem(position)

        if (currentItem != null) {
            textViewName.text = currentItem.getCurrencyCod()
        }

        return convertView
    }
}
