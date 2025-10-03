package com.example.proyectocolegiomovil

import android.os.Bundle
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TeacherHomeActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var bottomNav: BottomNavigationView

    // Calendar
    private lateinit var btnPrev: ImageButton
    private lateinit var btnNext: ImageButton
    private lateinit var txtMonth: TextView
    private lateinit var calendarView: CalendarView
    private val monthCal: Calendar = Calendar.getInstance()

    // Subject buttons
    private lateinit var btnCalcVer: MaterialButton
    private lateinit var btnFisicaVer: MaterialButton
    private lateinit var btnProgVer: MaterialButton

    // Quick actions
    private lateinit var cardAsistencia: MaterialCardView
    private lateinit var cardMaterial: MaterialCardView
    private lateinit var cardComunicado: MaterialCardView

    private val monthFormatter by lazy {
        // Spanish month like "septiembre 2025"
        SimpleDateFormat("MMMM yyyy", Locale("es", "ES"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_home)

        bindViews()
        setupToolbar()
        setupBottomNav()
        setupCalendarSection()
        setupSubjects()
        setupQuickActions()
    }

    private fun bindViews() {
        toolbar = findViewById(R.id.top_bar)
        bottomNav = findViewById(R.id.bottom_nav)

        btnPrev = findViewById(R.id.btn_month_prev)
        btnNext = findViewById(R.id.btn_month_next)
        txtMonth = findViewById(R.id.txt_month)
        calendarView = findViewById(R.id.calendarView)

        btnCalcVer = findViewById(R.id.btn_calc_ver)
        btnFisicaVer = findViewById(R.id.btn_fisica_ver)
        btnProgVer = findViewById(R.id.btn_prog_ver)

        cardAsistencia = findViewById(R.id.card_asistencia)
        cardMaterial = findViewById(R.id.card_material)
        cardComunicado = findViewById(R.id.card_comunicado)
    }

    private fun setupToolbar() {
        // Top AppBar menu (search / notifications)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_search -> {
                    toast("Buscar")
                    true
                }
                R.id.action_notifications -> {
                    toast("Notificaciones")
                    true
                }
                else -> false
            }
        }
    }

    private fun setupBottomNav() {
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true // already here
                R.id.nav_subjects -> {
                    // TODO: startActivity(Intent(this, SubjectsActivity::class.java))
                    toast("Ir a Asignaturas")
                    true
                }
                R.id.nav_calendar -> {
                    // TODO: startActivity(Intent(this, CalendarActivity::class.java))
                    toast("Ir a Calendario")
                    true
                }
                R.id.nav_comms -> {
                    // If you already use MainChatActivity for messages, you can navigate there:
                    // startActivity(Intent(this, MainChatActivity::class.java))
                    toast("Ir a Comunicados")
                    true
                }
                else -> false
            }
        }
    }

    private fun setupCalendarSection() {
        // Initialize month label from CalendarView current date
        monthCal.timeInMillis = calendarView.date
        monthCal.set(Calendar.DAY_OF_MONTH, 1)
        updateMonthLabel()

        btnPrev.setOnClickListener {
            monthCal.add(Calendar.MONTH, -1)
            applyMonthToCalendarView()
        }

        btnNext.setOnClickListener {
            monthCal.add(Calendar.MONTH, 1)
            applyMonthToCalendarView()
        }

        // Optional: react to day taps
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            toast("Seleccionado: %02d/%02d/%04d".format(dayOfMonth, month + 1, year))
        }
    }

    private fun applyMonthToCalendarView() {
        // Jump CalendarView to the first day of the selected month
        val millis = monthCal.timeInMillis
        calendarView.date = millis
        updateMonthLabel()
    }

    private fun updateMonthLabel() {
        // Capitalize first letter because Spanish months are lowercase by default
        val text = monthFormatter.format(monthCal.time).replaceFirstChar { it.titlecase(Locale("es", "ES")) }
        txtMonth.text = text
    }

    private fun setupSubjects() {
        btnCalcVer.setOnClickListener {
            // TODO: Navigate to subject detail (pass subject id or name)
            toast("Cálculo Diferencial → Ver")
        }
        btnFisicaVer.setOnClickListener {
            toast("Física Mecánica → Ver")
        }
        btnProgVer.setOnClickListener {
            toast("Programación → Ver")
        }
    }

    private fun setupQuickActions() {
        cardAsistencia.setOnClickListener {
            // TODO: startActivity(Intent(this, AttendanceActivity::class.java))
            toast("Tomar asistencia")
        }
        cardMaterial.setOnClickListener {
            // TODO: startActivity(Intent(this, UploadMaterialActivity::class.java))
            toast("Subir material")
        }
        cardComunicado.setOnClickListener {
            // TODO: startActivity(Intent(this, CreateAnnouncementActivity::class.java))
            toast("Crear comunicado")
        }

        // Optional: add ripple feedback to the inner icons if you want
        fun addClickableFeedback(iconId: Int, card: MaterialCardView) {
            card.findViewById<ImageView>(iconId)?.isHapticFeedbackEnabled = true
        }
    }

    private fun toast(msg: CharSequence) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
