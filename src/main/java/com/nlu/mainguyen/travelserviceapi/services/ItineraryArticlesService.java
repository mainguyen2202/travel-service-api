package com.nlu.mainguyen.travelserviceapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.ItineraryArticles;
import com.nlu.mainguyen.travelserviceapi.map.PathFinder;
import com.nlu.mainguyen.travelserviceapi.map.Tuple;
import com.nlu.mainguyen.travelserviceapi.map.Vert;
import com.nlu.mainguyen.travelserviceapi.entities.Articles;
import com.nlu.mainguyen.travelserviceapi.model.ItineraryArticlesDTO;
import com.nlu.mainguyen.travelserviceapi.model.ResponseDTO;
import com.nlu.mainguyen.travelserviceapi.repositories.ItineraryArticlesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.ArticlesRepository;
import com.nlu.mainguyen.travelserviceapi.repositories.ItinerariesRepository;

@Service
public class ItineraryArticlesService {
    @Autowired
    private ItineraryArticlesRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    private final ArticlesRepository articlesRepository;

    public ItineraryArticlesService(ItineraryArticlesRepository repository, ArticlesRepository articlesRepository,
            ItinerariesRepository itinerariesRepository) {
        this.repository = repository;
        this.articlesRepository = articlesRepository;
    }

    // lấy danh sách
    public List<ItineraryArticles> getAll() {
        return this.repository.findAll();
    }

    public List<ItineraryArticles> listByItineraryId(long itineraries_id, String date_start) {
        // if (date_start == null) {
        if (date_start == "") {
            return this.repository.findAllByIdItinerary(itineraries_id);
        }

        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // String date_str = format.format(date_start);// covert Date to String
        // System.out.println(date_str);
        return this.repository.findAllByDateStart(itineraries_id, date_start);
    }

    public Tuple<List<ItineraryArticles>, Double> listBySearch(
            long itineraries_id, String date_start, String GPSlatitude, String GPSlongitude) {
        // if (date_start == null) {
        if (date_start == "") {
            List<ItineraryArticles> ltsItineraryArticles = this.repository.findAllByIdItinerary(itineraries_id);
            return ShortestByDijkstra(GPSlatitude, GPSlongitude, ltsItineraryArticles);
        }
        return ShortestByDijkstra(GPSlatitude, GPSlongitude,
                this.repository.findAllByDateStart(itineraries_id, date_start));
    }

    public Tuple<List<ItineraryArticles>, Double> ShortestByDijkstra(String GPSlatitude, String GPSlongitude,
            List<ItineraryArticles> ltsItineraryArticles) {
        // Tạo vị trí của tôi để tìm đường đi ngắn nhất
        ItineraryArticles vGPS = new ItineraryArticles();
        vGPS.setId(-1L);
        vGPS.setArticles(new Articles(vGPS.getId(), "GPS", GPSlatitude, GPSlongitude));
        Vert GPS = new Vert(-1, "GPS", Double.parseDouble(GPSlatitude), Double.parseDouble(GPSlongitude), vGPS);

        Vert[] vertices = new Vert[ltsItineraryArticles.size() + 1];
        for (int i = 0; i < ltsItineraryArticles.size(); i++) {
            ItineraryArticles item = ltsItineraryArticles.get(i);
            Vert v = new Vert(item.getId(), item.getArticles().getName(),
                    Double.parseDouble(item.getArticles().getLatitude()),
                    Double.parseDouble(item.getArticles().getLongitude()), item);
            vertices[i] = v;
        }
        vertices[ltsItineraryArticles.size()] = GPS;

        // Khởi tạo PathFinder và tính toán đường đi ngắn nhất
        PathFinder shortestPath = new PathFinder();
        // Tính toán khoảng cách giữa các điểm
        shortestPath.InitGraph(vertices);
        // Tìm đường đi ngắn nhất qua tất cả các điểm
        shortestPath.ShortestPath(GPS);
        System.out.println("Vi vay, duong di ngan nhat la: " + shortestPath.getPath());
        System.out.println("Tong khoang cach: " + shortestPath.getTotalDistance());
        // Chuyển từ Vert sang ItineraryArticles
        List<ItineraryArticles> result = new ArrayList<ItineraryArticles>();
        for (Vert path : shortestPath.getPath()) {
            // Không trả về GPS
            if (path.getId() != -1)
                result.add((ItineraryArticles) path.getInfo());// ép kiểu dữ liệu từ Object về Đối tượng
        }
        return new Tuple<>(result, shortestPath.getTotalDistance());
    }

    public ResponseDTO create(ItineraryArticlesDTO dto) {
        try {
            ItineraryArticles ent = modelMapper.map(dto, ItineraryArticles.class);

            Optional<Articles> userOptional = articlesRepository.findById(dto.getArticles().getId());
            if (userOptional.isEmpty()) {
                return new ResponseDTO(2, "User not found");
            }
            Articles user = userOptional.get();
            ent.setArticles(user);

            // Kiểm tra xem đã tồn tại bản ghi với cặp ArticlesId và articlesId hay chưa
            Optional<ItineraryArticles> existingItineraryArticles = repository
                    .findByItinerariesIdAndArticlesId(ent.getItineraries().getId(), user.getId());
            if (existingItineraryArticles.isPresent()) {
                return new ResponseDTO(2, "articlesId and userId already exist");
            }

            ItineraryArticles created = repository.save(ent);
            ItineraryArticlesDTO responseDto = modelMapper.map(created, ItineraryArticlesDTO.class);
            return new ResponseDTO(1, "Created successfully", responseDto);
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    public ResponseDTO update(long id, ItineraryArticlesDTO dto) {
        try {
            Optional<ItineraryArticles> opl = this.repository.findById(id);
            if (opl.isEmpty()) {
                return null;// không tìm thấy dữ liệu return rỗng
            } else {
                // Cập nhật các trường của itineraries với các giá trị mới từ request
                ItineraryArticles ia = opl.get();
                ia.setDateStart(dto.getDateStart());

                ItineraryArticles update = this.repository.save(ia);
                ItineraryArticlesDTO responseDto = modelMapper.map(update, ItineraryArticlesDTO.class);

                return new ResponseDTO(1, "Update successfully", responseDto);
            }
        } catch (Exception e) {
            return new ResponseDTO(2, "Failed to create: " + e.getMessage());
        }
    }

    public ItineraryArticles getById(Long id) {
        Optional<ItineraryArticles> items = this.repository.findById(id);
        if (items.isPresent()) {
            return items.get();
        }
        return null;
    }

    public ResponseDTO deleteByID(Long id) {
        Optional<ItineraryArticles> opt = this.repository.findById(id);
        if (opt.isEmpty()) {
            return new ResponseDTO(2, "Empty");// không tìm thấy dữ liệu return lỗi
        } else {
            this.repository.deleteById(id);
            return new ResponseDTO(1, "Success");
        }
    }
}
