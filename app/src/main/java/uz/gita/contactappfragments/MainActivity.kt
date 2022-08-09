package uz.gita.contactappfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import uz.gita.contactappfragments.fragments.ContactsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contactFragment = ContactsFragment()
        openFragment(contactFragment)
    }

    fun openFragment(fm: Fragment) {
        if (!fragmentExist(fm))
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit)
                .replace(R.id.container_fragment, fm, fm.javaClass.name)
                .commit()
    }

    fun openFragmentWithBackStack(fm: Fragment) {
        if (!fragmentExist(fm))
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit)
                .replace(R.id.container_fragment, fm, fm.javaClass.name)
                .addToBackStack(fm.javaClass.name)
                .commit()
    }

    fun popUp() {
        supportFragmentManager.popBackStack()
    }

    private fun fragmentExist(fm: Fragment): Boolean {
        return supportFragmentManager.findFragmentByTag(fm.javaClass.name) != null
    }
}