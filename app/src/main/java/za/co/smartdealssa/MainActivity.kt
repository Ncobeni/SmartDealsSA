package za.co.smartdealssa

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Green = Color(0xFF137A45)
private val Ink = Color(0xFF17231C)
private val Muted = Color(0xFF66736B)
private val Pale = Color(0xFFF3F8F4)

data class Deal(
    val title: String,
    val category: String,
    val description: String,
    val price: String = "Check current price",
    val url: String = ""
)

// Approved affiliate catalogue. Verify retailer details before publishing.
private val starterDeals = listOf(
    Deal(
        "Car Jump Starter & Tyre Inflator",
        "Automotive",
        "Explore this automotive product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/car-jump-starter-tyre-inflator?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Portable 7.5L Cooling and Warming Car Refrigerator",
        "Automotive",
        "Explore this automotive product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/portable-7-5l-cooling-and-warming-car-refrigerator?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "U-Shape Cold Light Automatic Toothbrush - Pink",
        "Beauty",
        "Explore this beauty product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/u-shape-cold-light-automatic-toothbrush-pink?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Solar-Powered Indoor & Outdoor Solar Pendant Light",
        "Home",
        "Explore this home product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/solar-powered-indoor-outdoor-solar-pendant-light?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Smart Foldable Drone",
        "Electronics",
        "Explore this electronics product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/smart-foldable-drone?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Professional Fog Machine for Parties",
        "Lifestyle",
        "Explore this lifestyle product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/professional-fog-machine-for-parties?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "COLMI P10 Smart Watch - Black",
        "Electronics",
        "Explore this electronics product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/colmi-p10-smart-watch-black?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Beautiful Glamour Eyelashes Set",
        "Beauty",
        "Explore this beauty product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/beautiful-glamour-eyelashes-set?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Kids Table and Chair Study Set - Pink",
        "Toys & Kids",
        "Explore this toys & kids product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/kids-table-and-chair-study-set-pink?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Jeronimo Sand and Water Table",
        "Toys & Kids",
        "Explore this toys & kids product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/sand-water-table-jeronimo?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Car Windscreen Umbrella Sun Shade",
        "Automotive",
        "Explore this automotive product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/car-windscreen-umbrella-sun-shade?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Master Magnetic Construction Toy",
        "Toys & Kids",
        "Explore this toys & kids product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/master-magnetic-construction-toy?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Mini Air Conditioning Fan Humidifier",
        "Home",
        "Explore this home product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/mini-air-conditioning-fan-humidifier?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Fashionable Bluetooth Wireless Stereo Headphones W820",
        "Electronics",
        "Explore this electronics product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/fashionable-bluetooth-wireless-stereo-headphones-w820?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "60W Charger Power Adapter for Apple MacBook - Type 2",
        "Electronics",
        "Explore this electronics product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/60w-charger-power-adapter-for-apple-macbook-type-2?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Outdoor Foldable Travel Hiking Backpack - Blue",
        "Fashion",
        "Explore this fashion product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/outdoor-foldable-travel-hiking-backpack-blue?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "360-Degree Rotation 2-Tier Freestanding Kitchen Organizer Storage Rack",
        "Home",
        "Explore this home product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/360-degree-rotation-2-tier-freestanding-kitchen-organizer-storage-rack?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Kids Dump Truck XXL Ride-On - Red",
        "Toys & Kids",
        "Explore this toys & kids product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/kids-dump-truck-xxl-ride-on-red?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "Baby Food Processor",
        "Baby",
        "Explore this baby product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/baby-food-processor?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "RG59 High Quality Coaxial Cable - 100m",
        "Electronics",
        "Explore this electronics product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/rg59-high-quality-coxial-cable-100m?sca_ref=12731048.qYdMXHsZXknR"
    ),
    Deal(
        "K&F Concept Smart Lightweight Camera & Phone Tripod",
        "Electronics",
        "Explore this electronics product. Check the retailer page for specifications, price and availability.",
        price = "Check current price on Check It SA",
        url = "https://checkitsa.co.za/products/k-f-concept-smart-lightweight-camera-phone-tripod?sca_ref=12731048.qYdMXHsZXknR"
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SmartDealsApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartDealsApp() {
    val context = LocalContext.current
    var query by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("All") }
    var selected by remember { mutableStateOf<Deal?>(null) }
    val categories = listOf("All", "Automotive", "Electronics", "Home", "Beauty", "Fashion", "Lifestyle", "Toys & Kids", "Baby")

    val filtered = starterDeals.filter {
        (category == "All" || it.category == category) &&
        (it.title.contains(query, true) || it.description.contains(query, true))
    }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Green,
            onPrimary = Color.White,
            background = Pale,
            surface = Color.White,
            onSurface = Ink
        )
    ) {
        Scaffold(
            containerColor = Pale,
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text("Smart Deals SA", fontWeight = FontWeight.Bold, fontSize = 21.sp)
                            Text("Find useful deals. Share smart.", fontSize = 12.sp, color = Muted)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Pale)
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp)
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search products") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp)
                )
                Spacer(Modifier.height(12.dp))
                Text("Browse categories", fontWeight = FontWeight.Bold, color = Ink)
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categories.forEach { item ->
                        FilterChip(
                            selected = category == item,
                            onClick = { category = item },
                            label = { Text(item) }
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))
                Text("Deals you can shop", fontWeight = FontWeight.Bold, fontSize = 19.sp)
                Text("Check retailer pages for current prices and availability.", color = Muted, fontSize = 12.sp)
                Spacer(Modifier.height(8.dp))
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(filtered) { deal ->
                        Card(
                            modifier = Modifier.fillMaxWidth().clickable { selected = deal },
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text(deal.category.uppercase(), color = Green, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                Spacer(Modifier.height(4.dp))
                                Text(deal.title, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                                Text(deal.description, color = Muted, fontSize = 13.sp)
                                Spacer(Modifier.height(10.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(deal.price, fontWeight = FontWeight.SemiBold, color = Ink, modifier = Modifier.weight(1f))
                                    Text("VIEW DETAILS  →", color = Green, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                }
                            }
                        }
                    }
                    item {
                        Text(
                            "Disclosure: Smart Deals SA may earn a commission when you buy through an approved affiliate link. Prices and availability can change.",
                            color = Muted, fontSize = 11.sp, modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                }
            }
        }

        selected?.let { deal ->
            AlertDialog(
                onDismissRequest = { selected = null },
                title = { Text(deal.title) },
                text = {
                    Column {
                        Text(deal.description)
                        Spacer(Modifier.height(8.dp))
                        Text(deal.price, fontWeight = FontWeight.Bold)
                    }
                },
                confirmButton = {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        if (deal.url.isNotBlank()) {
                            TextButton(onClick = {
                                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(deal.url)))
                            }) {
                                Text("Buy Now")
                            }
                        }
                        TextButton(onClick = {
                            val text = "${deal.title}\\n${deal.description}\\n${deal.price}\\n\\nAffiliate disclosure: I may earn a commission if you buy through my link."
                            val send = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, if (deal.url.isBlank()) text else "$text\\n${deal.url}")
                            }
                            context.startActivity(Intent.createChooser(send, "Share deal"))
                        }) {
                            Icon(Icons.Default.Share, contentDescription = null)
                            Spacer(Modifier.width(4.dp))
                            Text("Share")
                        }
                    }
                },
                dismissButton = {
                    TextButton(onClick = { selected = null }) { Text("Close") }
                }
            )
        }
    }
}
