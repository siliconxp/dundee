package com.strv.dundee.model.entity

import android.os.Parcel
import com.google.firebase.firestore.ServerTimestamp
import com.strv.ktools.KParcelable
import com.strv.ktools.parcelableCreator
import com.strv.ktools.readDate
import com.strv.ktools.writeDate
import java.util.Date

/*
User object

Notes:
- data class needs to have default value so that it has empty constructor ready for Firestore
- properties also need to be vars because Firestore needs setters
 */
data class Wallet(
	var uid: String? = null,
	var coin: String? = null,
	var amount: Double? = null,
	@ServerTimestamp var created: Date? = null,
	var boughtPrice: Double? = null,
	var boughtCurrency: String? = null,
	var boughtDate: Date? = null
) : Document(), KParcelable {

	private constructor(parcel: Parcel) : this(
		uid = parcel.readString(),
		coin = parcel.readString(),
		amount = parcel.readValue(Double::class.java.classLoader) as? Double,
		created = parcel.readDate(),
		boughtPrice = parcel.readValue(Double::class.java.classLoader) as? Double,
		boughtCurrency = parcel.readString(),
		boughtDate = parcel.readDate()) {
		docId = parcel.readString()
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(uid)
		parcel.writeString(coin)
		parcel.writeValue(amount)
		parcel.writeDate(created)
		parcel.writeValue(boughtPrice)
		parcel.writeString(boughtCurrency)
		parcel.writeDate(boughtDate)
		parcel.writeString(docId)
	}

	companion object {
		const val COLLECTION = "wallets"
		const val ATTR_COIN = "coin"
		const val ATTR_BOUGHT_DATE = "boughtDate"
		@JvmField
		val CREATOR = parcelableCreator(::Wallet)
	}
}



