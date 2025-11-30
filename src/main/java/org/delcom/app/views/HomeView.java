package org.delcom.app.views;

import org.delcom.app.entities.User;
import org.delcom.app.utils.ConstUtil;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeView {

    // Kita tidak butuh Service di sini karena komunikasi data via REST API (JavaScript)
    public HomeView() {
    }

    @GetMapping("/")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Cek login
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/auth/login"; // Sesuaikan jika path login Anda berbeda
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof User)) {
            return "redirect:/auth/logout";
        }

        User authUser = (User) principal;
        
        // Kirim data user ke view untuk sambutan "Halo, NamaUser"
        model.addAttribute("user", authUser);
        
        // Tidak perlu mengirim 'TodoForm' atau 'CashFlow' object.
        // Form akan ditangani via JavaScript/JSON.

        return ConstUtil.TEMPLATE_PAGES_HOME;
    }
}